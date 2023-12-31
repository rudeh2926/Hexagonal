package com.example.hexagonal.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    val name: String
) {
    fun update(password : String) {
        this.password = password
    }
}