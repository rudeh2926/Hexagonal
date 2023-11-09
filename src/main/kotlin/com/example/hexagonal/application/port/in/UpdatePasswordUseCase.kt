package com.example.hexagonal.application.port.`in`

import com.example.hexagonal.adapter.dto.request.UpdatePasswordRequest

interface UpdatePasswordUseCase {
    fun update(request: UpdatePasswordRequest)
}