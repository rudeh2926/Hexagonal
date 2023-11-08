package com.example.hexagonal.adapter.out.persistence

import com.example.hexagonal.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email : String) : Optional<User>
}