package com.app.harho.model.propogator.productDetail

data class PropogatorProductDetailResponse(
    val body: List<Body>,
    val code: Int,
    val current_utc: String,
    val message: String,
    val success: Boolean
)