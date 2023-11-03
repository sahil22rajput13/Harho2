package com.app.harho.model.question

data class DailyQuizQuestionAnswer(
    val answer: Int,
    val created_at: String,
    val detail1: String,
    val detail2: String,
    val id: Int,
    val list: Int,
    val question: String,
    val question_type: Int,
    val status: Boolean,
    val updated_at: String
)