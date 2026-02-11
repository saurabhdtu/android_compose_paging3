package com.sample.compose.ui.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sample.compose.R
import com.sample.compose.ui.common.LottieAnim
import com.sample.compose.ui.common.navigation.AppRoutes
import com.sample.compose.data.network.ApiResponse

@Composable
fun SplashRoute(
    onFinished: (route:String) -> Unit,
    splashViewModel: SplashViewModel,
) {
    val authState by splashViewModel.apiResponse

    LaunchedEffect(Unit) {
        splashViewModel.isUserLoggedIn()
    }

    LaunchedEffect(authState) {
        if (authState is ApiResponse.Success) {
            if((authState as ApiResponse.Success<Boolean>).data){
                onFinished(AppRoutes.Home)
            }else {
                onFinished(AppRoutes.Login)
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center
    ) {
        when (val state = authState) {
            is ApiResponse.Error -> {
                Column {
                    LottieAnim(modifier = Modifier.fillMaxSize(fraction = 1f), R.raw.lottie_splash_loader)
                    Text(
                        text = state.message,
                        style = MaterialTheme.typography.h6
                    )
                }
            }
            ApiResponse.Loading,
            is ApiResponse.Success -> {
                Column {
                    LottieAnim(modifier = Modifier.fillMaxSize(fraction = 1f), R.raw.lottie_splash_loader)
                    Text(
                        text = "Splash Screen",
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        }

    }
}
