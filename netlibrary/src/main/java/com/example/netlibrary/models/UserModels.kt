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
    val userFirstName: String?,
    val userMiddleName: String?,
    val userLastName: String?,
    val birthDate: String?,
    val gender: String?
)


data class UserCreateRequest(
    val email: String,
    val password: String,
    val passwordConfirm: String,
    val userMiddleName: String,
    val userLastName: String,
    val birthDate: String,
    val gender: String,
    val userFirstName: String? = null
)