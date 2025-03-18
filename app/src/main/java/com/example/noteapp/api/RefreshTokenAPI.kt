package com.example.noteapp.api

import com.example.noteapp.models.auth.TokenRequest
import com.example.noteapp.models.auth.TokenResponse
import com.example.noteapp.utilities.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenAPI {
    @POST("/token?key=${Constants.API_KEY}")
    suspend fun refreshToken(@Body token: TokenRequest): Response<TokenResponse>
}