package com.example.hexagonal.adapter.`in`.web

import com.example.hexagonal.adapter.dto.request.UserLoginRequest
import com.example.hexagonal.adapter.dto.request.UserSignupRequest
import com.example.hexagonal.adapter.dto.response.QueryUserResponse
import com.example.hexagonal.application.port.`in`.QueryUserUseCase
import com.example.hexagonal.application.port.`in`.UserLoginUseCase
import com.example.hexagonal.application.port.`in`.UserSignupUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController(
    private val userSignupUseCase: UserSignupUseCase,
    private val userLoginUseCase: UserLoginUseCase,
    private val queryUserUseCase: QueryUserUseCase
) {
    @PostMapping
    fun signup(userSignupRequest: UserSignupRequest) =
        userSignupUseCase.signup(userSignupRequest)

    @PostMapping("/login")
    fun login(userLoginRequest: UserLoginRequest) =
        userLoginUseCase.login(userLoginRequest)

    @GetMapping("/all")
    fun queryAllUser() =
        queryUserUseCase.queryAllUser()
}