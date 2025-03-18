package com.example.noteapp.models.auth

data class AuthRequest (
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)