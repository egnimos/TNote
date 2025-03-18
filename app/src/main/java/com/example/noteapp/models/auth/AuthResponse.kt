package com.example.noteapp.models.auth

data class AuthResponse(
    // idToken string 	A Firebase Auth ID token for the authenticated user.
    val idToken: String,
    //email string 	The email for the authenticated user.
    val email: String,
    //refreshToken 	string 	A Firebase Auth refresh token for the authenticated user.
    val refreshToken: String,
    //expiresIn 	string 	The number of seconds in which the ID token expires.
    val expiresIn: String,
    //localId string The uid of the authenticated user.
    val localId: String,
    //registered boolean Whether the email is for an existing account.
    val registered: Boolean?
)
