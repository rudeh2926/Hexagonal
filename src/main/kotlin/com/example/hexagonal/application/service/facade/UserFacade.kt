package com.example.hexagonal.application.service.facade

import com.example.hexagonal.application.port.`in`.UserFacadeUseCase
import com.example.hexagonal.application.port.out.UserRepositoryPort
import com.example.hexagonal.domain.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepositoryPort: UserRepositoryPort
) : UserFacadeUseCase{

    override fun currentUser(): User {
        val email = SecurityContextHolder.getContext().authentication.name
        return getUserByEmail(email)
    }

    override fun getUserByEmail(email: String): User =
        userRepositoryPort.findByEmail(email)
}