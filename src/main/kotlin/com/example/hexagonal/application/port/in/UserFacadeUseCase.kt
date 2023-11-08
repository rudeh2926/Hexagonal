package com.example.hexagonal.application.port.`in`

import com.example.hexagonal.domain.User

interface UserFacadeUseCase {
    fun currentUser() : User

    fun getUserByEmail(email : String) : User
}