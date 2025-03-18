package com.example.noteapp.models.notes

import java.util.Date

data class Note(
    val id: String,
    val userId: String,
    val title: String,
    val description: String,
    val setReminderAt: Date,
    val daysToRemind: List<String>,
    val createdAt: Date,
    val updatedAt: Date
)
