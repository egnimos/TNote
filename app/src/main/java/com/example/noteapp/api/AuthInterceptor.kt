package com.example.noteapp.api

import android.util.Log
import com.example.noteapp.models.auth.TokenData
import com.example.noteapp.utilities.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tkManager: TokenManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalUrl = chain.request().url.newBuilder()

        // get the token
        val tokenData = runBlocking { try {
            tkManager.getToken()
        } catch (e: Exception) {
            Log.e("AUTH-INTERCEPTOR", "GET TOKEN FAILED WHILE SENDING REQUEST: ${e.message}")
            null
        }}


        if (tokenData?.idToken.isNullOrEmpty()) {
            return chain.proceed(request.build())
        }

        // if token is not null create new uri with token attach to it 
        val newUrl = originalUrl.addQueryParameter("auth", tokenData!!.idToken).build()

        return chain.proceed(request.url(newUrl).build())
    }
}