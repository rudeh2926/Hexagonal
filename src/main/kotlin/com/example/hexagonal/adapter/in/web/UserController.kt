package com.example.hexagonal.adapter.`in`.web

import com.example.hexagonal.adapter.dto.request.UserSignupRequest
import com.example.hexagonal.application.port.`in`.UserSignupUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController(
    private val userSignupUseCase: UserSignupUseCase
) {
    @PostMapping
    fun signup(userSignupRequest: UserSignupRequest) =
        userSignupUseCase.signup(userSignupRequest)
}