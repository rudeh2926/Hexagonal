package com.example.hexagonal.adapter.out.persistence

import com.example.hexagonal.adapter.dto.response.QueryUserResponse
import com.example.hexagonal.application.port.out.UserRepositoryPort
import com.example.hexagonal.common.PersistenceAdapter
import com.example.hexagonal.domain.User

@PersistenceAdapter
class UserPersistenceAdapter(
    private val userRepository: UserRepository
) : UserRepositoryPort {
    override fun findByEmail(email: String): User {
        return userRepository.findByEmail(email).orElseThrow { Exception("adsf") }
    }

    override fun saveUser(user: User) {
        userRepository.save(user)
    }

    override fun findAllUser(): List<QueryUserResponse> {
        val users = userRepository.findAll()
        return users.map { user ->
            QueryUserResponse(
                email = user.email,
                name = user.name
            )
        }
    }
}

