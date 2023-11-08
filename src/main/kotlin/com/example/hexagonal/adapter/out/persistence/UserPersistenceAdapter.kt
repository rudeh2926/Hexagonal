package com.example.hexagonal.adapter.out.persistence

import com.example.hexagonal.application.port.out.UserRepositoryPort
import com.example.hexagonal.domain.User

class UserPersistenceAdapter(
    private val userRepository: UserRepository
) : UserRepositoryPort {
    override fun findByEmail(email: String) {
      userRepository.findByEmail(email)
    }

    override fun saveUser(user: User) {
       userRepository.save(user)
    }
}