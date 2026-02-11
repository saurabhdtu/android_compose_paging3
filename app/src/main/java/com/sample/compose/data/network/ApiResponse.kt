package com.sample.compose.data.network

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String, val exception: Exception? = null) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}
