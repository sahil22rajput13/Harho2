package com.app.harho.model.question

data class Body(
    val created_at: String,
    val daily_quiz_question_answer: DailyQuizQuestionAnswer,
    val id: Int,
    val question_id: Int,
    val updated_at: String
)