package com.example.hexagonal.adapter.dto.response

data class TokenResponse (
    val accessToken: String,
    val expiredAt: Long
)