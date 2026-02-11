package com.sample.compose.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.compose.ui.screen.dogs.BreedListScreen
import com.sample.compose.ui.screen.login.LoginRoute
import com.sample.compose.ui.screen.splash.SplashRoute

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.DogsList,
        modifier = modifier
    ) {
        composable(AppRoutes.Splash) { backStackEntry ->
            SplashRoute(
                splashViewModel = hiltViewModel(backStackEntry),
                onFinished = {route->
                    navController.navigate(route) {
                        popUpTo(AppRoutes.Splash) { inclusive = true }
                    }
                }
            )
        }
        composable(AppRoutes.Login) {
            LoginRoute(
                loginViewModel = hiltViewModel(it)
            )
        }
        composable(AppRoutes.DogsList) {
            BreedListScreen(
                breedViewModel = hiltViewModel(it)
            )
        }
    }
}
