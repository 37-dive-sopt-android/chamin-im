package com.sopt.dive.signup

data class SignUpUiState(
    val id: String = "",
    val password: String = "",
    val nickname: String = "",
    val email: String = "",
    val age: String = "",
    val idError: String = "",
    val passwordError: String = "",
    val nicknameError: String = "",
    val emailError: String = "",
    val ageError: String = "",
    val isFormValid: Boolean = false
)