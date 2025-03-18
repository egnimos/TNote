package com.example.noteapp.api

import retrofit2.Response
import com.example.noteapp.models.notes.Note
import com.example.noteapp.models.notes.NoteResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NoteAPI {
    // get list of notes data of specific user
    @GET("/notes.json")
    suspend fun getUserNotes(@Query("userId") userId: String): Response<List<Note>>
    // create a note
    @POST("/notes.json")
    suspend fun createUserNote(@Body note: Note): Response<NoteResponse>
    // update a note
    @PATCH("/notes/{noteId}.json")
    suspend fun updateUserNote(@Path("noteId") noteId: String, @Body note: Note): Response<Note>

    // delete a note
    @DELETE("/notes/{noteId}.json")
    suspend fun deleteUserNote(@Path("noteId") noteId: String): Response<Unit>
}