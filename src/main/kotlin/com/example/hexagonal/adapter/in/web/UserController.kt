package com.example.hexagonal.adapter.`in`.web

import com.example.hexagonal.adapter.dto.request.UpdatePasswordRequest
import com.example.hexagonal.adapter.dto.request.UserLoginRequest
import com.example.hexagonal.adapter.dto.request.UserSignupRequest
import com.example.hexagonal.application.port.`in`.QueryUserMyInfoUseCase
import com.example.hexagonal.application.port.`in`.UpdatePasswordUseCase
import com.example.hexagonal.application.port.`in`.UserLoginUseCase
import com.example.hexagonal.application.port.`in`.UserSignupUseCase
import com.example.hexagonal.common.WebAdapter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
@RequestMapping("/user")
class UserController(
    private val userSignupUseCase: UserSignupUseCase,
    private val userLoginUseCase: UserLoginUseCase,
    private val passwordUseCase: UpdatePasswordUseCase,
    private val queryUserMyInfoUseCase: QueryUserMyInfoUseCase
) {
    @PostMapping
    fun signup(@RequestBody userSignupRequest: UserSignupRequest) =
        userSignupUseCase.signup(userSignupRequest)

    @PostMapping("/login")
    fun login(@RequestBody userLoginRequest: UserLoginRequest) =
        userLoginUseCase.login(userLoginRequest)

    @PatchMapping("/password")
    fun updatePassword(@RequestBody updatePasswordRequest: UpdatePasswordRequest) =
        passwordUseCase.update(updatePasswordRequest)

    @GetMapping("/my")
    fun queryMyInfo() =
        queryUserMyInfoUseCase.getMyInfo()
}