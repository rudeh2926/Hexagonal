package com.example.hexagonal.application.service

import com.example.hexagonal.adapter.dto.response.QueryUserMyInfoResponse
import com.example.hexagonal.application.port.`in`.QueryUserMyInfoUseCase
import com.example.hexagonal.application.port.`in`.UserFacadeUseCase
import com.example.hexagonal.common.UseCase
import org.springframework.transaction.annotation.Transactional

@UseCase
class QueryUserMyInfoService (
    private val userFacadeUseCase: UserFacadeUseCase
) : QueryUserMyInfoUseCase{
    @Transactional(readOnly = true)
    override fun getMyInfo(): QueryUserMyInfoResponse {
        val user = userFacadeUseCase.currentUser()
        return QueryUserMyInfoResponse(user.email, user.name, user.password)
    }
}