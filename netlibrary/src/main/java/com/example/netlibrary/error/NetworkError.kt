package com.example.netlibrary.error

sealed class NetworkError {
    object NoInternet : NetworkError()
    data class Api(val code: Int, val message: String?) : NetworkError()
    object Unknown : NetworkError()
}