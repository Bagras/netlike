package com.example.netlibrary.models

data class Order(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val user: String,
    val totalAmount: Double,
    val status: String
)

data class OrderCreateRequest(
    val user: String,
    val totalAmount: Double,
    val status: String
)

data class OrderItem(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val order: String,
    val product: String,
    val price: Double,
    val quantity: Int
)

data class OrderItemCreateRequest(
    val order: String,
    val product: String,
    val price: Double,
    val quantity: Int
)
