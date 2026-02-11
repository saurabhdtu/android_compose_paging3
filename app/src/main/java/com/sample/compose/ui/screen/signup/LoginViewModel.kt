package com.sample.compose.ui.screen.signup

import androidx.lifecycle.ViewModel
import com.sample.compose.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginRepository: LoginRepository): ViewModel() {

}