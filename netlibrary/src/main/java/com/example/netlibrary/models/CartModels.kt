package com.example.netlibrary.models

data class Cart(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val user: String
)

data class CartCreateRequest(
    val user: String
)

data class CartItem(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val cart: String,
    val product: String,
    val quantity: Int
)

data class CartItemCreateRequest(
    val cart: String,
    val product: String,
    val quantity: Int
)

data class CartItemUpdateRequest(
    val quantity: Int
)
