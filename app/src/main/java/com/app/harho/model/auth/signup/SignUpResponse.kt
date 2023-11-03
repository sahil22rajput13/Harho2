package com.app.harho.model.auth.signup

data class SignUpResponse(
    val body: Body,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)