package com.example.netlibrary.datasource

import com.example.netlibrary.api.ApiService
import com.example.netlibrary.error.NetworkError
import com.example.netlibrary.error.NetworkException
import com.example.netlibrary.models.AuthRequest
import com.example.netlibrary.models.AuthResponse
import com.example.netlibrary.models.Cart
import com.example.netlibrary.models.CartItem
import com.example.netlibrary.models.CreateCartRequest
import com.example.netlibrary.models.Order
import com.example.netlibrary.models.Product
import com.example.netlibrary.models.Project
import com.example.netlibrary.models.ProjectCreateRequest
import com.example.netlibrary.models.Stock
import com.example.netlibrary.models.User
import com.example.netlibrary.models.UserCreateRequest
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun login(identity: String, password: String): AuthResponse {
        val response = apiService.login(AuthRequest(identity, password))
        if (!response.isSuccessful) {
            throw NetworkException(
                NetworkError.Api(response.code(), response.errorBody()?.string())
            )
        }
        return response.body()!!

    }

    suspend fun register(request: UserCreateRequest): User {
        return try {
            apiService.register(request)
        }  catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: HttpException) {
            throw NetworkException(
                NetworkError.Api(e.code(), e.response()?.errorBody()?.string())
            )
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }
    }

    suspend fun getStock(): List<Stock> {
        return try {
            val response = apiService.getStocks()

            if (!response.isSuccessful) {
                throw NetworkException(
                    NetworkError.Api(
                        response.code(),
                        response.errorBody()?.string()
                    )
                )
            }

            response.body()?.items ?: emptyList()
                ?: throw NetworkException(NetworkError.Unknown)

        } catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        }
    }



    suspend fun getProducts(): List<Product>{
        return try {
            val response = apiService.getProducts()

            if (!response.isSuccessful) {
                throw NetworkException(
                    NetworkError.Api(
                        response.code(),
                        response.errorBody()?.string()
                    )
                )
            }

            response.body()?.items ?: emptyList()
            ?: throw NetworkException(NetworkError.Unknown)

        } catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        }
    }

    suspend fun searchProducts(query: String){
        return try {
            apiService.searchProducts(query)
        }  catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: HttpException) {
            throw NetworkException(
                NetworkError.Api(e.code(), e.response()?.errorBody()?.string())
            )
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }
    }

    suspend fun getUserCart(userId: String, token: String): List<CartItem> {
        return try {
            val filter = "user = '$userId'"
            val response = apiService.getUserCart("Bearer $token", filter)

            if (!response.isSuccessful) {
                throw NetworkException(
                    NetworkError.Api(
                        response.code(),
                        response.errorBody()?.string()
                    )
                )
            }

            response.body()?.items ?: emptyList()
            ?: throw NetworkException(NetworkError.Unknown)

        } catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        }
    }

    suspend fun createCart(userId: String, token: String): List<CartItem> {
        val request = CreateCartRequest(
            userId = userId,
            lastUpdated = "2026-02-11T12:26:57.501Z"
        )

        return try {
            val response = apiService.createCart("Bearer $token", request)

            if (!response.isSuccessful) {
                throw NetworkException(
                    NetworkError.Api(
                        response.code(),
                        response.errorBody()?.string()
                    )
                )
            }

            response.body()?.items ?: emptyList()
            ?: throw NetworkException(NetworkError.Unknown)

        } catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        }
    }

    suspend fun updateCart(request: List<CartItem>): Cart{
        return try {
            apiService.updateCart(request)
        }  catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: HttpException) {
            throw NetworkException(
                NetworkError.Api(e.code(), e.response()?.errorBody()?.string())
            )
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }
    }

    suspend fun clearCart(): Response<Unit> {
        return try {
            apiService.clearCart()
        }  catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: HttpException) {
            throw NetworkException(
                NetworkError.Api(e.code(), e.response()?.errorBody()?.string())
            )
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }
    }

    suspend fun createOrder(): Order {
        return try {
            apiService.createOrder()
        } catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: HttpException) {
            throw NetworkException(
                NetworkError.Api(e.code(), e.response()?.errorBody()?.string())
            )
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }
    }

    suspend fun getProject(): List<Project>{
        return try {
            apiService.getProjects()
        }  catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: HttpException) {
            throw NetworkException(
                NetworkError.Api(e.code(), e.response()?.errorBody()?.string())
            )
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }
    }

    suspend fun createProject(query: ProjectCreateRequest): Project{
        return try {
            apiService.createProject(query)
        }  catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: HttpException) {
            throw NetworkException(
                NetworkError.Api(e.code(), e.response()?.errorBody()?.string())
            )
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }
    }

    suspend fun getUser(id: String): User{
        return try {
            apiService.getUser(id)
        }  catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: HttpException) {
            throw NetworkException(
                NetworkError.Api(e.code(), e.response()?.errorBody()?.string())
            )
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }
    }

    suspend fun refreshToken(): AuthResponse{
        return try {
            apiService.refreshToken()
        }  catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        } catch (e: HttpException) {
            throw NetworkException(
                NetworkError.Api(e.code(), e.response()?.errorBody()?.string())
            )
        } catch (e: Exception) {
            throw NetworkException(NetworkError.Unknown)
        }
    }
}