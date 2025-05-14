package com.example.toeic_test_preparation_app.data.api

import com.example.toeic_test_preparation_app.data.remote.UserRequest
import com.example.toeic_test_preparation_app.data.remote.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun loginUser(@Body request: UserRequest): Call<UserResponse>
}
