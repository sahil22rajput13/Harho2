package com.app.harho.model.auth.userme

data class UserMeResponse(
    val body: Body,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)