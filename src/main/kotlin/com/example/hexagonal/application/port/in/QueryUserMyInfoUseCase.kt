package com.example.hexagonal.application.port.`in`

import com.example.hexagonal.adapter.dto.response.QueryUserMyInfoResponse

interface QueryUserMyInfoUseCase {
    fun getMyInfo() : QueryUserMyInfoResponse
}