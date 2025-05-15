package com.example.toeic_test_preparation_app.data.api

import com.example.toeic_test_preparation_app.data.remote.UserRequest
import com.example.toeic_test_preparation_app.data.remote.UserResponse
import com.example.toeic_test_preparation_app.data.remote.VocabularyTopicResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(@Body request: UserRequest): Call<UserResponse>

    @GET("user/topic/findAll")
    fun findAllTopic(): Call<List<VocabularyTopicResponse>>
}
