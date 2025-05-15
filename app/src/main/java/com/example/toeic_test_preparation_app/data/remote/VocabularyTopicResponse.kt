package com.example.toeic_test_preparation_app.data.remote

data class VocabularyTopicResponse (
    val vocabularyTopicId : Int,
    val name : String,
    val des : String,
    val images : String,
    val totalWord : Int,
    val totalLesson : Int
)