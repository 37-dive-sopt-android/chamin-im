package com.sopt.dive.login

data class LoginUiState(
    val id: String = "",
    val password: String = "",
    val idError: String = "",
    val passwordError: String = ""
)