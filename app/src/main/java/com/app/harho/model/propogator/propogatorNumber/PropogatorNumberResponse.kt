package com.app.harho.model.propogator.propogatorNumber

data class PropogatorNumberResponse(
    val body: List<Body>,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)