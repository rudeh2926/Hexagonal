package com.example.hexagonal.application.port.`in`

import com.example.hexagonal.adapter.dto.request.UserSignupRequest

interface UserSignupUseCase {
    fun signup(userSignupRequest: UserSignupRequest)
}