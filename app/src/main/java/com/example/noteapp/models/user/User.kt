package com.example.noteapp.models.user

import java.util.Date

data class User(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val email: String,
    val description: String?,
    val imageURL: String?,
    val createdAt: Date,
    val updatedAt: Date
)
