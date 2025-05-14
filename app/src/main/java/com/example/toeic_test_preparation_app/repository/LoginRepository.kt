package com.example.toeic_test_preparation_app.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.toeic_test_preparation_app.AuthManager
import com.example.toeic_test_preparation_app.data.api.RetrofitClient
import com.example.toeic_test_preparation_app.data.remote.UserRequest
import com.example.toeic_test_preparation_app.data.remote.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(private val context: Context) {
    fun login(request: UserRequest): LiveData<Result<UserResponse>> {
        val result = MutableLiveData<Result<UserResponse>>()

        RetrofitClient.apiService.login(request).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()!!
                    val authManager = AuthManager(context)
                    authManager.saveToken(loginResponse.user.token)
                    result.value = Result.success(loginResponse)
                } else {
                    result.value = Result.failure(Exception("Lỗi: ${response.code()}"))
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                result.value = Result.failure(t)
            }
        })
        return result
    }
}
