package com.example.netlibrary.api

import com.example.netlibrary.models.AuthRequest
import com.example.netlibrary.models.AuthResponse
import com.example.netlibrary.models.Cart
import com.example.netlibrary.models.CartItem
import com.example.netlibrary.models.CreateCartRequest
import com.example.netlibrary.models.Order
import com.example.netlibrary.models.PaginatedResponse
import com.example.netlibrary.models.Product
import com.example.netlibrary.models.Project
import com.example.netlibrary.models.ProjectCreateRequest
import com.example.netlibrary.models.Stock
import com.example.netlibrary.models.User
import com.example.netlibrary.models.UserCreateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Авторизация
    @POST("collections/users/auth-with-password")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    // Создание профиля
    @POST("collections/users/records")
    suspend fun register(@Body request: UserCreateRequest): User

    @GET("collections/stocks/records")
    suspend fun getStocks(): Response<PaginatedResponse<Stock>>

    // Получение каталога
    @GET("collections/products/records")
    suspend fun getProducts (): Response<PaginatedResponse<Product>>

    // Поиск
    @GET("collections/products/records")
    suspend fun searchProducts(
        @Query("filter") query: String
    )


    @GET("api/collections/carts/records")
    suspend fun getUserCart(
        @Header("Authorization") token: String,
        @Query("filter") filter: String = "user='{userId}'"
    ): Response<Cart>

    @POST("collections/carts/records")
    suspend fun createCart(
        @Header("Authorization") token: String,
        @Body request: CreateCartRequest
    ): Response<Cart>

    // Изменение корзины
    @POST("collections/cart/records")
    suspend fun updateCart(@Body request: List<CartItem>): Cart

    @DELETE("collections/cart/records")
    suspend fun clearCart(): Response<Unit>

    // Оформление заказа
    @POST("collections/orders/records")
    suspend fun createOrder(): Order

    // Список проектов
    @GET("collections/projects/records")
    suspend fun getProjects(): List<Project>

    // Создание проекта
    @POST("collections/projects/records")
    suspend fun createProject(@Part query: ProjectCreateRequest): Project

    // Получение информации о пользователе
    @GET("collections/users/records/{id}")
    suspend fun getUser(@Path("id") id: String): User

    // Выход из профиля
    @POST("collection/users/auth-refresh")
    suspend fun refreshToken(): AuthResponse




}