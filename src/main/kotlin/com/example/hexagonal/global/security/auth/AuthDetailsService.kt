package com.example.hexagonal.global.security.auth

import com.example.hexagonal.application.service.facade.UserFacade
import com.example.hexagonal.domain.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userFacade: UserFacade
) : UserDetailsService {
    override fun loadUserByUsername(email : String?) : UserDetails {
        val user: User? = email?.let { userFacade.getUserByEmail(it) }
        return AuthDetails(user!!.email)
    }
}