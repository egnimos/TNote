package com.example.noteapp.utilities

import android.content.Context
import android.content.SharedPreferences
import com.example.noteapp.api.RefreshTokenAPI
import com.example.noteapp.models.auth.TokenData
import com.example.noteapp.models.auth.TokenRequest
import com.example.noteapp.models.auth.TokenResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import android.util.Base64
import com.google.gson.Gson
import org.json.JSONObject

class TokenManager  @Inject constructor(
    @ApplicationContext context: Context,
    private val refreshTokenAPI: RefreshTokenAPI
) {

    private var prefs: SharedPreferences = context.
    getSharedPreferences(Constants.TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveToken(tokenData: TokenData) {
        val editor = prefs.edit()
        val gson = Gson()
        val json  = gson.toJson(tokenData)
        editor.putString(Constants.TOKEN_DATA, json)
        editor.apply()
    }

    suspend fun getToken(): TokenData {
        val gson = Gson()
        val json = prefs.getString(Constants.TOKEN_DATA, null)
        val tk = json?.let { gson.fromJson(it, TokenData::class.java) }
        // check if the token is valid
        if (isTokenValid(tk?.idToken)) {
            return tk!!
        }
        // if the token is in valid
        // create a new token
        val tr = getIDToken(tk)
        val newTokenData = TokenData(
            expiresIn = tr.expiresIn,
            refreshToken = tr.refreshToken,
            idToken = tr.idToken,
            userId = tr.userId,
        )
        //save the new token
        saveToken(newTokenData)
        return newTokenData
    }

    //get the ID token
    private suspend fun getIDToken(tokenData: TokenData?): TokenResponse {
        if (tokenData == null) throw Exception("Token Refresh Failed: Invalid Token Data")

        val tokenReq = TokenRequest(
            grantType = "refresh_token",
            refreshToken = tokenData.refreshToken
        )
        val response = refreshTokenAPI.refreshToken(tokenReq)
        if (response.isSuccessful) {
            // if response body is null then throw exception
            return response.body() ?: throw Exception("Response Body is empty")
        } else {
            throw Exception("Token Refresh Failed: ${response.errorBody()?.string()}")
        }
    }

    // is token valid check for the token expiration
    private fun isTokenValid(idToken: String?): Boolean {
        // if the token is null then it means invalid
        if (idToken.isNullOrEmpty()) return false

        return try {
            val tokenParts = idToken.split(".")
            if (tokenParts.size < 2) return false
            val payload = String(
                Base64.decode(tokenParts[1], Base64.URL_SAFE), StandardCharsets.UTF_8)
            val json = JSONObject(payload)
            // convert to milliseconds and also in Long to prevent mismatch type
            val exp = json.getLong("exp") * 1000L
            val currentMills = System.currentTimeMillis()

            // if the difference of exp and current mills is more than 60 seconds then we set the
            // token valid as expiration is more than 1 min
            exp - currentMills > 60000
        } catch (e: Exception) {
            false
        }
    }
}