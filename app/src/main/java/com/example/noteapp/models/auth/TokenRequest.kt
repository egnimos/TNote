package com.example.noteapp.models.auth

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("grant_type") val grantType: String,
    @SerializedName("refresh_token") val refreshToken: String,
)
