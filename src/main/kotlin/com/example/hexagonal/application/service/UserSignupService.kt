package com.example.hexagonal.application.service

import com.example.hexagonal.adapter.dto.request.UserSignupRequest
import com.example.hexagonal.application.port.`in`.UserSignupUseCase
import com.example.hexagonal.application.port.out.UserRepositoryPort
import com.example.hexagonal.domain.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserSignupService(
    private val userRepositoryPort: UserRepositoryPort,
    private val passwordEncoder : PasswordEncoder
) : UserSignupUseCase {

    @Transactional
    override fun signup(request: UserSignupRequest) {
        val user = User(
            email = request.email,
            password = request.password,
            name = request.name,
            id = null
        )
        userRepositoryPort.saveUser(user)
    }
}