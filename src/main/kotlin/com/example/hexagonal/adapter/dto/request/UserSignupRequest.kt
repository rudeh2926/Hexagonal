package com.example.hexagonal.adapter.dto.request

data class UserSignupRequest (
    val email : String,
    val password : String,
    val name : String
)