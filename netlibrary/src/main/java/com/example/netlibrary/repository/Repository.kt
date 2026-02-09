package com.example.netlibrary.repository

import com.example.netlibrary.models.AuthResponse
import com.example.netlibrary.models.Cart
import com.example.netlibrary.models.CartItem
import com.example.netlibrary.models.Order
import com.example.netlibrary.models.Product
import com.example.netlibrary.models.Project
import com.example.netlibrary.models.Stock
import com.example.netlibrary.models.User
import retrofit2.Response
import retrofit2.http.Body

interface Repository {
    suspend fun login(email: String, password: String): AuthResponse

    suspend fun register(
        email: String,
        password: String,
        passwordConfirm: String,
        surname: String,
        patronymic: String,
        birthDate: String,
        gender: String,
        name: String? = null
    ): User

    suspend fun getStocks(): List<Stock>

    suspend fun getProducts (): List<Product>

    suspend fun searchProducts(query: String)

    suspend fun getCart(): Cart

    suspend fun updateCart(@Body request: List<CartItem>): Cart

    suspend fun clearCart(): Response<Unit>

    suspend fun createOrder(): Order

    suspend fun getProjects(): List<Project>

    suspend fun createProject(
        title: String,
        type: String,
        startDate: String,
        endDate: String,
        descriptionSource: String,
        category: String,
        image: String?
    ): Project

    suspend fun getUser(id: String): User

    suspend fun refreshToken(): AuthResponse


}