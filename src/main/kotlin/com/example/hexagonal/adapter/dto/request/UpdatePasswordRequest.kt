package com.example.hexagonal.adapter.dto.request

data class UpdatePasswordRequest (
    val validPassword : String,
    val newPassword : String
)