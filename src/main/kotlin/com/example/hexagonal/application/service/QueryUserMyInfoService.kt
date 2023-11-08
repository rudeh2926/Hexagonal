package com.example.hexagonal.application.service

import com.example.hexagonal.adapter.dto.response.QueryUserResponse
import com.example.hexagonal.application.port.`in`.QueryUserMyInfoUseCase
import com.example.hexagonal.application.port.`in`.UserFacadeUseCase
import com.example.hexagonal.application.port.out.UserRepositoryPort
import com.example.hexagonal.common.UseCase
import org.springframework.transaction.annotation.Transactional

@UseCase
class QueryUserMyInfoService (
    private val userRepositoryPort : UserRepositoryPort,
    private val userFacadeUseCase: UserFacadeUseCase
) : QueryUserMyInfoUseCase{
    @Transactional(readOnly = true)
    override fun getMyInfo(): QueryUserResponse {
        val user = userFacadeUseCase.currentUser()
       userRepositoryPort.findByEmail(user.email)
        return QueryUserResponse(user.email, user.name)
    }
}