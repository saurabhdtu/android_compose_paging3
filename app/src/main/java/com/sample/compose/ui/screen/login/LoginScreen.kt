package com.sample.compose.ui.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.compose.ui.common.TextInput

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel
) {
    val loginState by loginViewModel.loginState
    LoginScreen(
        loginState = loginState,
        onEmailChanged = loginViewModel::isEmailValid,
        onPasswordChanged = loginViewModel::isPasswordValid,
    )
}

@Composable
fun LoginScreen(
    loginState: LoginScreenStates,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginScreenState = loginState as? LoginScreenStates.LoginState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(weight = 0.2f))
        Text("Login", style = MaterialTheme.typography.h2)
        Spacer(modifier = Modifier.weight(weight = 0.05f))
        TextInput(
            modifier = Modifier.fillMaxWidth(),
            key = "email",
            label = "Email",
            placeholder = "you@example.com",
            value = email,
            leadingIconRes = null,
            trailingIconRes = null,
            keyboardType = KeyboardType.Email,
            onValueChange = { value ->
                email = value
                onEmailChanged(value)
            }
        )
        loginScreenState?.emailError?.let { error ->
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = error,
                color = MaterialTheme.colors.error,
                modifier = Modifier.fillMaxWidth().align(alignment = Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextInput(
            modifier = Modifier.fillMaxWidth(),
            key = "password",
            label = "Password",
            placeholder = "Enter your password",
            value = password,
            leadingIconRes = null,
            trailingIconRes = null,
            keyboardType = KeyboardType.Password,
            onValueChange = { value ->
                password = value
                onPasswordChanged(value)
            }
        )
        loginScreenState?.passwordError?.let { error ->
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = error, color = MaterialTheme.colors.error,
                modifier = Modifier.fillMaxWidth().align(alignment = Alignment.Start))
        }

        Spacer(modifier = Modifier.height(height = 20.dp))
        Button( onClick={

        }) { }

        Spacer(modifier = Modifier.weight(weight = 0.6f))

    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            loginState = LoginScreenStates.LoginState(isEmailValid = false, isPasswordValid = false, emailError = "Invalid email", passwordError = "Password error"),
            onEmailChanged = {},
            onPasswordChanged = {}
        )
    }
}
