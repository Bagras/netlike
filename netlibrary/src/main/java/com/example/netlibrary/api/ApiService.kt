package com.example.netlibrary.api

import com.example.netlibrary.models.AuthRequest
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
import com.example.netlibrary.models.Stock
import com.example.netlibrary.models.User
import com.example.netlibrary.models.UserCreateRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("collections/users/auth-with-password")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @POST("collections/users/records")
    suspend fun register(@Body request: UserCreateRequest): Response<User>

    @GET("collections/users/records/{id}")
    suspend fun getUserById(
        @Path("id") userId: String,
        @Header("Authorization") bearerToken: String
    ): Response<User>

    @GET("collections/products/records")
    suspend fun getProducts(
        @Query("page") page: Int = 1,
        @Query("perPage") perPage: Int = 30,
        @Query("filter") filter: String? = null,
        @Query("sort") sort: String? = null
    ): Response<PaginatedResponse<Product>>

    @GET("collections/products/records/{id}")
    suspend fun getProductById(@Path("id") productId: String): Response<Product>

    @GET("collections/stocks/records")
    suspend fun getStocks(
        @Query("page") page: Int = 1,
        @Query("perPage") perPage: Int = 30
    ): Response<PaginatedResponse<Stock>>

    @GET("collections/stocks/records/{id}")
    suspend fun getStockById(@Path("id") stockId: String): Response<Stock>

    @GET("collections/carts/records")
    suspend fun getCarts(
        @Header("Authorization") bearerToken: String,
        @Query("filter") filter: String? = null,
        @Query("expand") expand: String? = null
    ): Response<PaginatedResponse<Cart>>

    @POST("collections/carts/records")
    suspend fun createCart(
        @Header("Authorization") bearerToken: String,
        @Body request: CartCreateRequest
    ): Response<Cart>

    @GET("collections/carts/records/{id}")
    suspend fun getCartById(
        @Header("Authorization") bearerToken: String,
        @Path("id") cartId: String
    ): Response<Cart>

    @DELETE("collections/carts/records/{id}")
    suspend fun deleteCart(
        @Header("Authorization") bearerToken: String,
        @Path("id") cartId: String
    ): Response<Unit>

    @GET("collections/cart_items/records")
    suspend fun getCartItems(
        @Header("Authorization") bearerToken: String,
        @Query("filter") filter: String,
        @Query("expand") expand: String? = null
    ): Response<PaginatedResponse<CartItem>>

    @POST("collections/cart_items/records")
    suspend fun createCartItem(
        @Header("Authorization") bearerToken: String,
        @Body request: CartItemCreateRequest
    ): Response<CartItem>

    @GET("collections/cart_items/records/{id}")
    suspend fun getCartItemById(
        @Header("Authorization") bearerToken: String,
        @Path("id") cartItemId: String
    ): Response<CartItem>

    @PATCH("collections/cart_items/records/{id}")
    suspend fun updateCartItem(
        @Header("Authorization") bearerToken: String,
        @Path("id") cartItemId: String,
        @Body request: CartItemUpdateRequest
    ): Response<CartItem>

    @DELETE("collections/cart_items/records/{id}")
    suspend fun deleteCartItem(
        @Header("Authorization") bearerToken: String,
        @Path("id") cartItemId: String
    ): Response<Unit>

    @GET("collections/orders/records")
    suspend fun getOrders(
        @Header("Authorization") bearerToken: String,
        @Query("filter") filter: String,
        @Query("sort") sort: String? = null
    ): Response<PaginatedResponse<Order>>

    @POST("collections/orders/records")
    suspend fun createOrder(
        @Header("Authorization") bearerToken: String,
        @Body request: OrderCreateRequest
    ): Response<Order>

    @GET("collections/orders/records/{id}")
    suspend fun getOrderById(
        @Header("Authorization") bearerToken: String,
        @Path("id") orderId: String
    ): Response<Order>

    @POST("collections/order_items/records")
    suspend fun createOrderItem(
        @Header("Authorization") bearerToken: String,
        @Body request: OrderItemCreateRequest
    ): Response<OrderItem>

    @GET("collections/projects/records")
    suspend fun getProjects(
        @Header("Authorization") bearerToken: String,
        @Query("filter") filter: String
    ): Response<PaginatedResponse<Project>>

    @Multipart
    @POST("collections/projects/records")
    suspend fun createProject(
        @Header("Authorization") bearerToken: String,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part image: MultipartBody.Part
    ): Response<Project>

    @GET("collections/projects/records/{id}")
    suspend fun getProjectById(
        @Header("Authorization") bearerToken: String,
        @Path("id") projectId: String
    ): Response<Project>

    @PATCH("collections/projects/records/{id}")
    suspend fun updateProject(
        @Header("Authorization") bearerToken: String,
        @Path("id") projectId: String,
        @Body request: Map<String, @JvmSuppressWildcards Any>
    ): Response<Project>

    @DELETE("collections/projects/records/{id}")
    suspend fun deleteProject(
        @Header("Authorization") bearerToken: String,
        @Path("id") projectId: String
    ): Response<Unit>
}
