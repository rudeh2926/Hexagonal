package com.example.hexagonal.adapter.dto.response

import com.example.hexagonal.domain.User

data class QueryUserMyInfoResponse (
    var email : String,
    var name : String,
    var password : String
)