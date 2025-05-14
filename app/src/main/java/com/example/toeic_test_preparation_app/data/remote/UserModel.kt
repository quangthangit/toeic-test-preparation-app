package com.example.toeic_test_preparation_app.data.remote

data class UserModel(
    val username: String,
    val role: String,
    val isActive: Boolean,
    val token: String,
)
