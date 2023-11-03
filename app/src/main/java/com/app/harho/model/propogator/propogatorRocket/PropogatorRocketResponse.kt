package com.app.harho.model.propogator.propogatorRocket

data class PropogatorRocketResponse(
    val body: Body,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)