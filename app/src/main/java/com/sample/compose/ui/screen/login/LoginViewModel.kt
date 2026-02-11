package com.sample.compose.ui.screen.login

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.compose.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginRepository: LoginRepository) : ViewModel() {
    private val _loginState = mutableStateOf<LoginScreenStates>(LoginScreenStates.InitialState)

    val loginState: State<LoginScreenStates> = _loginState

    var emailValidity: Boolean = false
    var passwordValidity: Boolean = false
    var emailError: String? = null
    var passwordError: String? = null
    private var email: String? = null
        set(value) {
            field = value
        }
    private var password: String? = null
        set(value) {
            field = value
        }

    fun isEmailValid(e: String) {
        email = e
        if (Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
            emailValidity = true
            emailError = null
        } else {
            emailValidity = false
            emailError = "Email is not valid"
        }
        emitState()

    }

    fun emitState() {
        _loginState.value =
            LoginScreenStates.LoginState(
                isEmailValid = emailValidity,
                isPasswordValid = passwordValidity,
                emailError = emailError,
                passwordError = passwordError
            )
    }

    fun isPasswordValid(p: String) {
        password = p
        if (p.length > 5) {
            passwordValidity = true
            passwordError = null
        } else {
            passwordValidity = false
            passwordError = "Min length 5"
        }
        emitState()
    }

    fun submit() {
        viewModelScope.async {
            if (email != null && password != null) {
                _loginState.value = LoginScreenStates.Loader(true)
                 withContext(Dispatchers.IO) {
                     val job = async {
                         loginRepository.login(email = email!!, password = password!!)
                     }
                    val response = job.await()
//                    if (response.data == true) {
//                        _loginState.value = LoginScreenStates.Loader(false)
//                    } else {
//                        _loginState.value = LoginScreenStates.Loader(false)
                }


            }

        }

    }

}
