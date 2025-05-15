package com.example.toeic_test_preparation_app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.toeic_test_preparation_app.data.api.RetrofitClient
import com.example.toeic_test_preparation_app.data.remote.VocabularyTopicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VocabularyTopicRepository {
    fun getAllVocabularyTopics(): LiveData<Result<List<VocabularyTopicResponse>>> {
        val result = MutableLiveData<Result<List<VocabularyTopicResponse>>>()

        RetrofitClient.apiService.findAllTopic().enqueue(object : Callback<List<VocabularyTopicResponse>> {
            override fun onResponse(
                call: Call<List<VocabularyTopicResponse>>,
                response: Response<List<VocabularyTopicResponse>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    result.value = Result.success(response.body()!!)
                } else {
                    result.value = Result.failure(Exception("Error: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<VocabularyTopicResponse>>, t: Throwable) {
                result.value = Result.failure(t)
            }
        })

        return result
    }
}