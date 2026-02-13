package com.example.netlibrary.repository

import com.example.netlibrary.datasource.RemoteDataSource
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
import com.example.netlibrary.models.UserCreateRequest
import okhttp3.MultipartBody

class RepositoryImpl(private val remote: RemoteDataSource) : Repository {
    override suspend fun login(identity: String, password: String): AuthResponse =
        remote.login(identity, password)

    override suspend fun register(request: UserCreateRequest): User =
        remote.register(request)

    override suspend fun getUserById(userId: String, token: String): User =
        remote.getUserById(userId, token)

    override suspend fun getProducts(page: Int, perPage: Int, filter: String?, sort: String?): PaginatedResponse<Product> =
        remote.getProducts(page, perPage, filter, sort)

    override suspend fun getProductById(productId: String): Product =
        remote.getProductById(productId)

    override suspend fun getStocks(page: Int, perPage: Int): PaginatedResponse<Stock> =
        remote.getStocks(page, perPage)

    override suspend fun getStockById(stockId: String): Stock =
        remote.getStockById(stockId)

    override suspend fun getCarts(token: String, filter: String?, expand: String?): PaginatedResponse<Cart> =
        remote.getCarts(token, filter, expand)

    override suspend fun createCart(token: String, request: CartCreateRequest): Cart =
        remote.createCart(token, request)

    override suspend fun getCartById(token: String, cartId: String): Cart =
        remote.getCartById(token, cartId)

    override suspend fun deleteCart(token: String, cartId: String) =
        remote.deleteCart(token, cartId)

    override suspend fun getCartItems(token: String, filter: String, expand: String?): PaginatedResponse<CartItem> =
        remote.getCartItems(token, filter, expand)

    override suspend fun createCartItem(token: String, request: CartItemCreateRequest): CartItem =
        remote.createCartItem(token, request)

    override suspend fun getCartItemById(token: String, cartItemId: String): CartItem =
        remote.getCartItemById(token, cartItemId)

    override suspend fun updateCartItem(token: String, cartItemId: String, request: CartItemUpdateRequest): CartItem =
        remote.updateCartItem(token, cartItemId, request)

    override suspend fun deleteCartItem(token: String, cartItemId: String) =
        remote.deleteCartItem(token, cartItemId)

    override suspend fun getOrders(token: String, filter: String, sort: String?): PaginatedResponse<Order> =
        remote.getOrders(token, filter, sort)

    override suspend fun createOrder(token: String, request: OrderCreateRequest): Order =
        remote.createOrder(token, request)

    override suspend fun getOrderById(token: String, orderId: String): Order =
        remote.getOrderById(token, orderId)

    override suspend fun createOrderItem(token: String, request: OrderItemCreateRequest): OrderItem =
        remote.createOrderItem(token, request)

    override suspend fun getProjects(token: String, filter: String): PaginatedResponse<Project> =
        remote.getProjects(token, filter)

    override suspend fun createProject(token: String, request: ProjectCreateRequest, image: MultipartBody.Part): Project =
        remote.createProject(token, request, image)

    override suspend fun getProjectById(token: String, projectId: String): Project =
        remote.getProjectById(token, projectId)

    override suspend fun updateProject(token: String, projectId: String, request: Map<String, Any>): Project =
        remote.updateProject(token, projectId, request)

    override suspend fun deleteProject(token: String, projectId: String) =
        remote.deleteProject(token, projectId)
}
