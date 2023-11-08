package com.example.hexagonal.application.port.`in`

import com.example.hexagonal.adapter.dto.request.UserLoginRequest
import com.example.hexagonal.adapter.dto.response.TokenResponse

interface UserLoginUseCase {

    fun login(request: UserLoginRequest) : TokenResponse
}