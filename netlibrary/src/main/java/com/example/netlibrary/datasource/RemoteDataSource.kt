package com.example.netlibrary.datasource

import com.example.netlibrary.api.ApiService
import com.example.netlibrary.error.NetworkError
import com.example.netlibrary.error.NetworkException
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
import com.example.netlibrary.models.ProjectCreateRequest
import com.example.netlibrary.models.Stock
import com.example.netlibrary.models.User
import com.example.netlibrary.models.UserCreateRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.IOException

class RemoteDataSource(private val apiService: ApiService) {

    private fun bearer(token: String): String = if (token.startsWith("Bearer ")) token else "Bearer $token"

    private suspend fun <T> safeApiCall(call: suspend () -> Response<T>): T {
        val response = try {
            call()
        } catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }

        if (!response.isSuccessful) {
            throw NetworkException(NetworkError.Api(response.code(), response.errorBody()?.string()))
        }

        return response.body() ?: throw NetworkException(NetworkError.Unknown)
    }

    private suspend fun safeUnitApiCall(call: suspend () -> Response<Unit>) {
        val response = try {
            call()
        } catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }

        if (!response.isSuccessful) {
            throw NetworkException(NetworkError.Api(response.code(), response.errorBody()?.string()))
        }
    }

    suspend fun login(identity: String, password: String): AuthResponse =
        safeApiCall { apiService.login(AuthRequest(identity, password)) }

    suspend fun register(request: UserCreateRequest): User =
        safeApiCall { apiService.register(request) }

    suspend fun getUserById(userId: String, token: String): User =
        safeApiCall { apiService.getUserById(userId, bearer(token)) }

    suspend fun getProducts(page: Int, perPage: Int, filter: String?, sort: String?): PaginatedResponse<Product> =
        safeApiCall { apiService.getProducts(page = page, perPage = perPage, filter = filter, sort = sort) }

    suspend fun getProductById(productId: String): Product =
        safeApiCall { apiService.getProductById(productId) }

    suspend fun getStocks(page: Int, perPage: Int): PaginatedResponse<Stock> =
        safeApiCall { apiService.getStocks(page = page, perPage = perPage) }

    suspend fun getStockById(stockId: String): Stock =
        safeApiCall { apiService.getStockById(stockId) }

    suspend fun getCarts(token: String, filter: String?, expand: String?): PaginatedResponse<Cart> =
        safeApiCall { apiService.getCarts(bearer(token), filter, expand) }

    suspend fun createCart(token: String, request: CartCreateRequest): Cart =
        safeApiCall { apiService.createCart(bearer(token), request) }

    suspend fun getCartById(token: String, cartId: String): Cart =
        safeApiCall { apiService.getCartById(bearer(token), cartId) }

    suspend fun deleteCart(token: String, cartId: String) =
        safeUnitApiCall { apiService.deleteCart(bearer(token), cartId) }

    suspend fun getCartItems(token: String, filter: String, expand: String?): PaginatedResponse<CartItem> =
        safeApiCall { apiService.getCartItems(bearer(token), filter, expand) }

    suspend fun createCartItem(token: String, request: CartItemCreateRequest): CartItem =
        safeApiCall { apiService.createCartItem(bearer(token), request) }

    suspend fun getCartItemById(token: String, cartItemId: String): CartItem =
        safeApiCall { apiService.getCartItemById(bearer(token), cartItemId) }

    suspend fun updateCartItem(token: String, cartItemId: String, request: CartItemUpdateRequest): CartItem =
        safeApiCall { apiService.updateCartItem(bearer(token), cartItemId, request) }

    suspend fun deleteCartItem(token: String, cartItemId: String) =
        safeUnitApiCall { apiService.deleteCartItem(bearer(token), cartItemId) }

    suspend fun getOrders(token: String, filter: String, sort: String?): PaginatedResponse<Order> =
        safeApiCall { apiService.getOrders(bearer(token), filter, sort) }

    suspend fun createOrder(token: String, request: OrderCreateRequest): Order =
        safeApiCall { apiService.createOrder(bearer(token), request) }

    suspend fun getOrderById(token: String, orderId: String): Order =
        safeApiCall { apiService.getOrderById(bearer(token), orderId) }

    suspend fun createOrderItem(token: String, request: OrderItemCreateRequest): OrderItem =
        safeApiCall { apiService.createOrderItem(bearer(token), request) }

    suspend fun getProjects(token: String, filter: String): PaginatedResponse<Project> =
        safeApiCall { apiService.getProjects(bearer(token), filter) }

    suspend fun createProject(token: String, request: ProjectCreateRequest, image: MultipartBody.Part): Project {
        val params: Map<String, RequestBody> = mapOf(
            "user" to request.user.toRequestBody(MultipartBody.FORM),
            "title" to request.title.toRequestBody(MultipartBody.FORM),
            "type" to request.type.toRequestBody(MultipartBody.FORM),
            "startDate" to request.startDate.toRequestBody(MultipartBody.FORM),
            "endDate" to request.endDate.toRequestBody(MultipartBody.FORM),
            "descriptionSource" to request.descriptionSource.toRequestBody(MultipartBody.FORM),
            "category" to request.category.toRequestBody(MultipartBody.FORM)
        )
        return safeApiCall { apiService.createProject(bearer(token), params, image) }
    }

    suspend fun getProjectById(token: String, projectId: String): Project =
        safeApiCall { apiService.getProjectById(bearer(token), projectId) }

    suspend fun updateProject(token: String, projectId: String, request: Map<String, Any>): Project =
        safeApiCall { apiService.updateProject(bearer(token), projectId, request) }

    suspend fun deleteProject(token: String, projectId: String) =
        safeUnitApiCall { apiService.deleteProject(bearer(token), projectId) }
}
