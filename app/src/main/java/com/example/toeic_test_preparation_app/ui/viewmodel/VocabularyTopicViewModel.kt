package com.example.toeic_test_preparation_app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toeic_test_preparation_app.data.remote.VocabularyTopicResponse
import com.example.toeic_test_preparation_app.repository.VocabularyTopicRepository

class VocabularyTopicViewModel : ViewModel() {
    private var repository = VocabularyTopicRepository()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _topics = MutableLiveData<Result<List<VocabularyTopicResponse>>>()
    val topics: LiveData<Result<List<VocabularyTopicResponse>>> = _topics

    fun loadTopicsIfNeeded() {
        if (_topics.value?.getOrNull().isNullOrEmpty()) {
            _loading.value = true
            repository.getAllVocabularyTopics().observeForever { result ->
                _topics.value = result
                _loading.value = false
            }
        }
    }
}