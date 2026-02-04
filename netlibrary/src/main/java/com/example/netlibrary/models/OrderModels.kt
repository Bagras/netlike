package com.example.netlibrary.models

data class OrderItem(
    val productId: String,
    val quantity: Int,
    val price: Float,
    val title: String?
)

data class Order(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,

    val user: String,
    val items: List<OrderItem>,
    val totalAmount: Float,
    val status: String
)
