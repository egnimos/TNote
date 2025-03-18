package com.example.noteapp.api

import com.example.noteapp.models.auth.AuthRequest
import com.example.noteapp.models.auth.AuthResponse
import com.example.noteapp.utilities.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("/accounts:signUp?key=${Constants.API_KEY}")
    suspend fun signUp(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("/accounts:signInWithPassword?key=${Constants.API_KEY}")
    suspend fun signIn(@Body authRequest: AuthRequest): Response<AuthResponse>
}