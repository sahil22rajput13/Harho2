package com.app.harho.adapter.propogatorDate

data class PropogatorDateResponse(
    val body: Body,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)