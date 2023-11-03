package com.app.harho.model.propogator.propogatorTittle

data class PropogatorTittleResponse(
    val body: Body,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)