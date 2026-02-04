package com.example.netlibrary.datasource

import com.example.netlibrary.api.ApiService
import com.example.netlibrary.error.NetworkError
import com.example.netlibrary.error.NetworkException
import com.example.netlibrary.models.AuthRequest
import com.example.netlibrary.models.AuthResponse
import com.example.netlibrary.models.Cart
import com.example.netlibrary.models.CartItem
import com.example.netlibrary.models.Order
import com.example.netlibrary.models.Product
import com.example.netlibrary.models.Project
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

            response.body()?.items
                ?: throw NetworkException(NetworkError.Unknown)

        } catch (e: IOException) {
            throw NetworkException(NetworkError.NoInternet)
        }
    }



    suspend fun getProducts(): List<Product>{
        return try {
            apiService.getProducts()
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

    suspend fun getCart(): Cart {
        return try {
            apiService.getCart()
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

    suspend fun createProject(query: Project): Project{
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