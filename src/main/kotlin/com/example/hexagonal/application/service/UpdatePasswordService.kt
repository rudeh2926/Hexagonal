package com.example.hexagonal.application.service

import com.example.hexagonal.adapter.dto.request.UpdatePasswordRequest
import com.example.hexagonal.application.port.`in`.UpdatePasswordUseCase
import com.example.hexagonal.application.port.`in`.UserFacadeUseCase
import com.example.hexagonal.common.UseCase
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

@UseCase
class UpdatePasswordService (
    private val userFacadeUseCase: UserFacadeUseCase,
    private val passwordEncoder: PasswordEncoder
) : UpdatePasswordUseCase {
    @Transactional
    override fun update(request: UpdatePasswordRequest) {
        val user = userFacadeUseCase.currentUser()
        if (!passwordEncoder.matches(user.password, request.password)) {
            throw Exception("1틀림")
        }
        if (request.validPassword != request.newPassword) {
            throw Exception("2틀림")
        }
        val password = passwordEncoder.encode(request.newPassword)
        user.update(password)
    }
}