package com.example.toeic_test_preparation_app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toeic_test_preparation_app.data.remote.UserRequest
import com.example.toeic_test_preparation_app.data.remote.UserResponse
import com.example.toeic_test_preparation_app.repository.LoginRepository

class LoginViewModel : ViewModel() {
    private var repository: LoginRepository? = null

    private val _loginResult = MutableLiveData<Result<UserResponse>>()
    val loginResult: LiveData<Result<UserResponse>> = _loginResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun login(context: Context, username: String, password: String) {
        repository = LoginRepository(context)
        _loading.value = true
        val request = UserRequest(username, password)
        repository?.login(request)?.observeForever {
            _loginResult.value = it
            _loading.value = false
        }
    }
}