package com.example.hexagonal.application.service

import com.example.hexagonal.adapter.dto.request.UserSignupRequest
import com.example.hexagonal.application.port.`in`.UserSignupUseCase
import com.example.hexagonal.application.port.out.UserRepositoryPort
import com.example.hexagonal.common.UseCase
import com.example.hexagonal.domain.User
import org.jetbrains.annotations.NotNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

@UseCase
class UserSignupService(
    private val userRepositoryPort: UserRepositoryPort,
    private val passwordEncoder : PasswordEncoder
) : UserSignupUseCase {

    @Transactional
    override fun signup(request: UserSignupRequest) {
        val user = User(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            name = request.name,
            id = null
        )
        userRepositoryPort.saveUser(user)
    }
}