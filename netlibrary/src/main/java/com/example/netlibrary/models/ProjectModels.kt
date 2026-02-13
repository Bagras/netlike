package com.example.netlibrary.models

data class Project(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val user: String,
    val title: String,
    val type: String,
    val startDate: String,
    val endDate: String,
    val descriptionSource: String,
    val category: String,
    val image: String
)

data class ProjectCreateRequest(
    val user: String,
    val title: String,
    val type: String,
    val startDate: String,
    val endDate: String,
    val descriptionSource: String,
    val category: String
)
