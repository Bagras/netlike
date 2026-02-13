package com.example.netlibrary.repository

import com.example.netlibrary.models.AuthResponse
import com.example.netlibrary.models.Cart
import com.example.netlibrary.models.CartCreateRequest
import com.example.netlibrary.models.CartItem
import com.example.netlibrary.models.CartItemCreateRequest
import com.example.netlibrary.models.CartItemUpdateRequest
import com.example.netlibrary.models.Order
import com.example.netlibrary.models.OrderCreateRequest
import com.example.netlibrary.models.OrderItem
import com.example.netlibrary.models.OrderItemCreateRequest
import com.example.netlibrary.models.PaginatedResponse
import com.example.netlibrary.models.Product
import com.example.netlibrary.models.Project
import com.example.netlibrary.models.ProjectCreateRequest
import com.example.netlibrary.models.Stock
import com.example.netlibrary.models.User
import okhttp3.MultipartBody

interface Repository {
    suspend fun login(identity: String, password: String): AuthResponse
    suspend fun register(request: com.example.netlibrary.models.UserCreateRequest): User
    suspend fun getUserById(userId: String, token: String): User

    suspend fun getProducts(page: Int = 1, perPage: Int = 30, filter: String? = null, sort: String? = null): PaginatedResponse<Product>
    suspend fun getProductById(productId: String): Product

    suspend fun getStocks(page: Int = 1, perPage: Int = 30): PaginatedResponse<Stock>
    suspend fun getStockById(stockId: String): Stock

    suspend fun getCarts(token: String, filter: String? = null, expand: String? = null): PaginatedResponse<Cart>
    suspend fun createCart(token: String, request: CartCreateRequest): Cart
    suspend fun getCartById(token: String, cartId: String): Cart
    suspend fun deleteCart(token: String, cartId: String)

    suspend fun getCartItems(token: String, filter: String, expand: String? = null): PaginatedResponse<CartItem>
    suspend fun createCartItem(token: String, request: CartItemCreateRequest): CartItem
    suspend fun getCartItemById(token: String, cartItemId: String): CartItem
    suspend fun updateCartItem(token: String, cartItemId: String, request: CartItemUpdateRequest): CartItem
    suspend fun deleteCartItem(token: String, cartItemId: String)

    suspend fun getOrders(token: String, filter: String, sort: String? = null): PaginatedResponse<Order>
    suspend fun createOrder(token: String, request: OrderCreateRequest): Order
    suspend fun getOrderById(token: String, orderId: String): Order
    suspend fun createOrderItem(token: String, request: OrderItemCreateRequest): OrderItem

    suspend fun getProjects(token: String, filter: String): PaginatedResponse<Project>
    suspend fun createProject(token: String, request: ProjectCreateRequest, image: MultipartBody.Part): Project
    suspend fun getProjectById(token: String, projectId: String): Project
    suspend fun updateProject(token: String, projectId: String, request: Map<String, Any>): Project
    suspend fun deleteProject(token: String, projectId: String)
}
