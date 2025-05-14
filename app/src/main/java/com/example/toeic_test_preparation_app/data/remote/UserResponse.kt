package com.example.toeic_test_preparation_app.data.remote

data class UserResponse(
    val message: String,
    val user: UserModel,
    val token : String,
)