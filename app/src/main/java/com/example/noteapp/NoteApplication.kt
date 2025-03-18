package com.example.noteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// this is the entry point of the application and hilt dependency injection and tells hilt
// to start generate from here
@HiltAndroidApp
class NoteApplication: Application() {
}