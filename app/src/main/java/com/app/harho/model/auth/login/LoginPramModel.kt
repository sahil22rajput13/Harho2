package com.app.harho.model.auth.login

data class LoginPramModel(
    val device_token: String,
    val device_type: String,
    val email: String,
    val refrence_id: String
)