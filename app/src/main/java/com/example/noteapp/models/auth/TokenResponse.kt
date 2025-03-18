package com.example.noteapp.models.auth

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("expires_in") val expiresIn: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("id_token") val idToken: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("project_id") val projectId: String
)


data class TokenData (
    val expiresIn: String,
    val refreshToken: String,
    val idToken: String,
    val userId: String
)
