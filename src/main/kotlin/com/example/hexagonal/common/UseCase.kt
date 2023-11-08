package com.example.hexagonal.common

import org.springframework.stereotype.Component

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@MustBeDocumented
@Component
annotation class UseCase()
