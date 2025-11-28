package com.sopt.dive.util

object LoginValidator {
    fun validateId(id: String): String {
        return when {
            id.isEmpty() -> "아이디를 입력해주세요."
            else -> ""
        }
    }

    fun validatePassword(password: String): String {
        return when {
            password.isEmpty() -> "비밀번호를 입력해주세요."
            else -> ""
        }
    }

    fun isAllValid(id: String, password: String): Boolean {
        return validateId(id).isEmpty() && validatePassword(password).isEmpty()
    }
}