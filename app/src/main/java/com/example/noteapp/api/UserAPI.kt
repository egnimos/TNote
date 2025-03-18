package com.example.noteapp.api
import com.example.noteapp.models.user.User
import com.example.noteapp.models.user.UserCreateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {
//    create a user profile
    @POST("/users.json")
    suspend fun createUserProfile(@Body user: User): Response<UserCreateResponse>
//    update specific user profile
    @PATCH("/users/{userId}.json")
    suspend fun updateUserProfile(@Path("userId") userId: String, @Body user: User): Response<User>
//    get user profile
    @GET("/users/{userId}.json")
    suspend fun getUserProfile(@Path("userId") userId: String): Response<User>
}