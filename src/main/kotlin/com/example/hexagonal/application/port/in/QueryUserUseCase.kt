package com.example.hexagonal.application.port.`in`

import com.example.hexagonal.adapter.dto.response.QueryUserResponse
import com.example.hexagonal.domain.User

interface QueryUserUseCase {
    fun queryAllUser() : List<QueryUserResponse>
}