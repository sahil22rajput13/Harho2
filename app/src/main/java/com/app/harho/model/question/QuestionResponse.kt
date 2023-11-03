package com.app.harho.model.question

data class QuestionResponse(
    val body: List<Body>,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)