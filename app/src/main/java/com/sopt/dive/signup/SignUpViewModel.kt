package com.sopt.dive.signup

import androidx.lifecycle.ViewModel
import com.sopt.dive.util.SignUpValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun updateId(id: String) {
        val error = if (id.isNotBlank()) SignUpValidator.validateId(id) else ""
        _uiState.update { currentState ->
            currentState.copy(
                id = id,
                idError = error,
                isFormValid = isFormValid(id, currentState.password, currentState.nickname, currentState.email, currentState.age)
            )
        }
    }

    fun updatePassword(password: String) {
        val error = if (password.isNotBlank()) SignUpValidator.validatePassword(password) else ""
        _uiState.update { currentState ->
            currentState.copy(
                password = password,
                passwordError = error,
                isFormValid = isFormValid(currentState.id, password, currentState.nickname, currentState.email, currentState.age)
            )
        }
    }

    fun updateNickname(nickname: String) {
        val error = if (nickname.isNotBlank()) SignUpValidator.validateNickname(nickname) else ""
        _uiState.update { currentState ->
            currentState.copy(
                nickname = nickname,
                nicknameError = error,
                isFormValid = isFormValid(currentState.id, currentState.password, nickname, currentState.email, currentState.age)
            )
        }
    }

    fun updateEmail(email: String) {
        val error = if (email.isNotBlank()) SignUpValidator.validateEmail(email) else ""
        _uiState.update { currentState ->
            currentState.copy(
                email = email,
                emailError = error,
                isFormValid = isFormValid(currentState.id, currentState.password, currentState.nickname, email, currentState.age)
            )
        }
    }

    fun updateAge(age: String) {
        val error = if (age.isNotBlank()) SignUpValidator.validateAge(age) else ""
        _uiState.update { currentState ->
            currentState.copy(
                age = age,
                ageError = error,
                isFormValid = isFormValid(currentState.id, currentState.password, currentState.nickname, currentState.email, age)
            )
        }
    }

    private fun isFormValid(id: String, password: String, nickname: String, email: String, age: String): Boolean {
        return SignUpValidator.isAllValid(id, password, nickname, email, age)
    }

    fun signUp(): Boolean {
        val currentState = _uiState.value
        return SignUpValidator.isAllValid(
            currentState.id,
            currentState.password,
            currentState.nickname,
            currentState.email,
            currentState.age
        )
    }
}