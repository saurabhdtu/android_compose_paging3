package com.sample.compose.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.sample.compose.data.network.ApiResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface LoginRepository {
    suspend fun isLoggedIn(): Boolean
    suspend fun login(email: String, password: String): ApiResponse<Boolean>
    suspend fun logout(): Boolean
}

@Singleton
class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    val TAG = " LoginRepositoryImpl"
    val auth = Firebase.auth
    override suspend fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override suspend fun login(email: String, password: String): ApiResponse<Boolean> {
        val task = auth.createUserWithEmailAndPassword(email, password)
        try {
            task.await()
            return ApiResponse.Success(true)
        } catch (e: Exception) {
            return ApiResponse.Error(e.message ?: "Unknown Error")
        }
    }

    override suspend fun logout(): Boolean {
        auth.signOut()
        return true
    }

}