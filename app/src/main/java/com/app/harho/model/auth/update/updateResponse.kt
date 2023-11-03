package com.app.harho.model.auth.update

data class updateResponse(
    val body: Body,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)