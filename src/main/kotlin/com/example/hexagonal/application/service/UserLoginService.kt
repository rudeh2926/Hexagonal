package com.example.hexagonal.application.service

import com.example.hexagonal.adapter.dto.request.UserLoginRequest
import com.example.hexagonal.adapter.dto.response.TokenResponse
import com.example.hexagonal.application.port.`in`.UserLoginUseCase
import com.example.hexagonal.application.port.out.UserRepositoryPort
import com.example.hexagonal.common.UseCase
import com.example.hexagonal.global.security.jwt.JwtProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

@UseCase
class UserLoginService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepositoryPort: UserRepositoryPort,
    private val jwtProvider: JwtProvider
) : UserLoginUseCase{
    @Transactional(readOnly = true)
    override fun login(request: UserLoginRequest) : TokenResponse {
        val user = userRepositoryPort.findByEmail(request.email) ?: throw Exception()
        if (passwordEncoder.matches(request.password, user.password)) {
            throw Exception()
        }
        return jwtProvider.getToken(user)
    }
}