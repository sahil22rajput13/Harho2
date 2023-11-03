package com.app.harho.model.propogator.propogatorProduct

data class PropogatorProductResponse(
    val body: List<Body>,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)