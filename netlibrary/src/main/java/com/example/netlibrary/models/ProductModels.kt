package com.example.netlibrary.models

data class Product(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val title: String,
    val description: String,
    val price: Double,
    val type: String,
    val category: String,
    val approximateCost: Double,
    val searchText: String
)
