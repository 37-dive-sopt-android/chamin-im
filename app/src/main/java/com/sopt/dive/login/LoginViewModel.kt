package com.sopt.dive.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateId(id: String) {
        _uiState.update { currentState ->
            currentState.copy(id = id)
        }
    }

    fun updatePassword(password: String) {
        _uiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    fun validateLogin(signUpId: String, signUpPw: String): LoginResult {
        val currentState = _uiState.value

        return when {
            currentState.id == signUpId && currentState.password == signUpPw -> {
                LoginResult.Success
            }
            signUpId.isEmpty() -> {
                LoginResult.NoSignUp
            }
            else -> {
                LoginResult.InvalidCredentials
            }
        }
    }
}

sealed class LoginResult {
    object Success : LoginResult()
    object NoSignUp : LoginResult()
    object InvalidCredentials : LoginResult()
}