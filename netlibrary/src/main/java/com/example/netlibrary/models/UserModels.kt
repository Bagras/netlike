package com.example.netlibrary.models

data class User(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val username: String,
    val email: String,
    val emailVisibility: Boolean,
    val verified: Boolean,
    val name: String?,
    val surname: String,
    val patronymic: String,
    val birthDate: String,
    val gender: String
)

data class UserCreateRequest(
    val email: String,
    val password: String,
    val passwordConfirm: String,
    val username: String,
    val surname: String,
    val patronymic: String,
    val birthDate: String,
    val gender: String,
    val name: String? = null
)
