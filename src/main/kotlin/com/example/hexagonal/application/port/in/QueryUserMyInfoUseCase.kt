package com.example.hexagonal.application.port.`in`

import com.example.hexagonal.adapter.dto.response.QueryUserResponse

interface QueryUserMyInfoUseCase {
    fun getMyInfo() : QueryUserResponse
}