package com.example.netlibrary.models

data class Stock(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val title: String,
    val price: Double,
    val image: String
) {
    val imageUrl: String
        get() = "http://192.168.21.39:8090/api/files/$collectionId/$id/$image"
}
