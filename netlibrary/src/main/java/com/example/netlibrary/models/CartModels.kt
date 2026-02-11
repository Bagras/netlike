package com.example.netlibrary.models

data class ProductData(
    val title: String,
    val price: Float
)

data class CartItem(
    val productId: String,
    val quantity: Int,
    val productData: ProductData? = null
)

data class Cart(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,

    val user: String,
    val items: List<CartItem>,
    val totalItems: Int,
    val totalPrice: Float
)


data class CreateCartRequest(
    val userId: String,
    val lastUpdated: String
)

