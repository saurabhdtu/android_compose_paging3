package com.sample.compose.ui.screen.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.compose.data.repository.LoginRepository
import com.sample.compose.data.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _apiResponse = mutableStateOf<ApiResponse<Boolean>>(ApiResponse.Loading)
    val apiResponse: State<ApiResponse<Boolean>> = _apiResponse

    suspend fun isUserLoggedIn() {
        viewModelScope.launch {
            val loginDeferred = async { loginRepository.isLoggedIn() }
            val delayDeferred = async { delay(1500) }
            val isLoggedIn = loginDeferred.await()
            delayDeferred.await()
            _apiResponse.value = ApiResponse.Success(isLoggedIn)
        }
    }
}