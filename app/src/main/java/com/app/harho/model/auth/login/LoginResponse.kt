package com.app.harho.model.auth.login

data class LoginResponse(
    val body: Body,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)