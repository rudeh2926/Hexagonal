package com.example.hexagonal.application.port.`in`

import com.example.hexagonal.adapter.dto.response.QueryUserResponse

interface QueryUserUseCase {
    fun queryAllUser() : List<QueryUserResponse>
}