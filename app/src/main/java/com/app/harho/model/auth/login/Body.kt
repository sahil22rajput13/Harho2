package com.app.harho.model.auth.login

data class Body(
    val token: String,
    val user: User
)