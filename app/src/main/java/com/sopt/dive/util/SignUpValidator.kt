package com.sopt.dive.util

object SignUpValidator {
    fun validateId(id: String): String {
        return when {
            id.isEmpty() -> "ID를 입력해주세요."
            id.contains(" ") -> "ID에는 공백을 사용할 수 없습니다."
            id.length !in 6..10 -> "ID는 6 ~ 10글자 사이여야 합니다."
            else -> ""
        }
    }

    fun validatePassword(password: String): String {
        return when {
            password.isEmpty() -> "비밀번호를 입력해주세요."
            password.contains(" ") -> "비밀번호에는 공백을 사용할 수 없습니다."
            password.length !in 8..12 -> "비밀번호는 8 ~ 12글자 사이여야 합니다."
            else -> ""
        }
    }

    fun validateNickname(nickname: String): String {
        return when {
            nickname.isEmpty() -> "닉네임을 입력해주세요."
            nickname.isBlank() || nickname.contains(" ") -> "닉네임에 공백을 사용할 수 없습니다."
            else -> ""
        }
    }

    fun validateDrink(drink: String): String {
        return when {
            drink.isEmpty() -> "주량을 입력해주세요."
            !drink.all { it.isDigit() } -> "숫자만 입력 가능합니다."
            else -> ""
        }
    }

    // 모든 필드 검사
    fun isAllValid(id: String, password: String, nickname: String, drink: String): Boolean {
        return validateId(id).isEmpty() &&
                validatePassword(password).isEmpty() &&
                validateNickname(nickname).isEmpty() &&
                validateDrink(drink).isEmpty()
    }
}