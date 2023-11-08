package com.example.hexagonal.adapter.out.persistence

import com.example.hexagonal.application.port.out.UserRepositoryPort
import com.example.hexagonal.domain.User

class UserPersistenceAdapter(
    private val userRepository: UserRepository
) : UserRepositoryPort {
    override fun findByEmail(email: String) :User {
      return userRepository.findByEmail(email)
          .orElseThrow()
    }

    override fun saveUser(user: User) {
       userRepository.save(user)
    }

    override fun findAllUser(): List<User> {
       return userRepository.findAll()
    }
}

