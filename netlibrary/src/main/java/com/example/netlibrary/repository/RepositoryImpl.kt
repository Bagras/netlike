package com.example.netlibrary.repository

import android.util.Log
import com.example.netlibrary.datasource.RemoteDataSource
import com.example.netlibrary.models.AuthResponse
import com.example.netlibrary.models.Cart
import com.example.netlibrary.models.CartItem
import com.example.netlibrary.models.Order
import com.example.netlibrary.models.Product
import com.example.netlibrary.models.Project
import com.example.netlibrary.models.Stock
import com.example.netlibrary.models.User
import com.example.netlibrary.models.UserCreateRequest
import retrofit2.Response

class RepositoryImpl(private val remote: RemoteDataSource) : Repository {
    override suspend fun login(
        email: String,
        password: String
    ): AuthResponse {
        Log.d("TESTING", "Зашел импл")
        return remote.login(email, password)
    }

    override suspend fun register(
        email: String,
        password: String,
        passwordConfirm: String,
        userMiddleName: String,
        userLastName: String,
        birthDate: String,
        gender: String,
        userFirstName: String?
    ): User {
        return remote.register(UserCreateRequest(
            email,
            password,
            passwordConfirm,
            userMiddleName,
            userLastName,
            birthDate,
            gender
        ))
    }

    override suspend fun getStocks(): List<Stock> {
        Log.d("TESTING", "Зашел импл")

        return remote.getStock()
    }

    override suspend fun getProducts(): List<Product> {
        return remote.getProducts()
    }

    override suspend fun searchProducts(query: String) {
        return remote.searchProducts(query)
    }

    override suspend fun getCart(): Cart {
        return remote.getCart()
    }

    override suspend fun updateCart(request: List<CartItem>): Cart {
        return remote.updateCart(request)
    }

    override suspend fun clearCart(): Response<Unit> {
        return remote.clearCart()
    }

    override suspend fun createOrder(): Order {
        return remote.createOrder()
    }

    override suspend fun getProjects(): List<Project> {
        return remote.getProject()
    }

    override suspend fun createProject(
        id: String,
        collectionId: String,
        collectionName: String,
        created: String,
        updated: String,
        user: String,
        title: String,
        type: String,
        startDate: String,
        endDate: String,
        descriptionSource: String,
        category: String,
        image: String?
    ): Project {
        return remote.createProject(Project(
            id,
            collectionId,
            collectionName,
            created,
            updated,
            user,
            title,
            type,
            startDate,
            endDate,
            descriptionSource,
            category,
            image
        ))
    }

    override suspend fun getUser(id: String): User {
        return remote.getUser(id)
    }

    override suspend fun refreshToken(): AuthResponse {
        return remote.refreshToken()
    }
}