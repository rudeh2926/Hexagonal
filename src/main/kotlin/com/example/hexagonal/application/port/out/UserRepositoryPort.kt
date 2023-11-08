package com.example.hexagonal.application.port.out

import com.example.hexagonal.adapter.dto.response.QueryUserResponse
import com.example.hexagonal.domain.User

interface UserRepositoryPort {
    fun saveUser(user: User)

    fun findByEmail(email : String) : User

    fun findAllUser() : List<QueryUserResponse>
}