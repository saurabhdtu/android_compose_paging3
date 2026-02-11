package com.sample.compose.ui

sealed class AsyncUiState {
    object Loading : AsyncUiState()
    data class Loaded<T>(val data: T) : AsyncUiState()
    data class Error(val message: String) : AsyncUiState()
}