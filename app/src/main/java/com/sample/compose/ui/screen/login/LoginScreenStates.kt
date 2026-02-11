package com.sample.compose.ui.screen.login

sealed class LoginScreenStates {
    object InitialState : LoginScreenStates()

    data class LoginState(
        val isEmailValid: Boolean,
        val isPasswordValid: Boolean,
        val emailError: String?,
        val passwordError: String?
    ) : LoginScreenStates()

    data class Loader(val showLoader: Boolean) : LoginScreenStates()

}