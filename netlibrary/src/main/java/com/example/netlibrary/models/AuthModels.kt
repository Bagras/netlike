package com.example.netlibrary.models

data class AuthRequest(
    val identity: String,
    val password: String
)

data class AuthResponse(
    val token: String,
    val record: User
)

