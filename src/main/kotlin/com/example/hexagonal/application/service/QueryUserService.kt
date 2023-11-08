package com.example.hexagonal.application.service

import com.example.hexagonal.adapter.dto.response.QueryUserResponse
import com.example.hexagonal.application.port.`in`.QueryUserUseCase
import com.example.hexagonal.application.port.out.UserRepositoryPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryUserService(
    private val userRepositoryPort: UserRepositoryPort
) : QueryUserUseCase {
    @Transactional(readOnly = true)
    override fun queryUser(): List<QueryUserResponse> {
        val users = userRepositoryPort.findAllUser()
        val queryUserResponses = users.map { user ->
            QueryUserResponse(
                email = user.email,
                name = user.name
            )
        }
        return queryUserResponses
    }
}