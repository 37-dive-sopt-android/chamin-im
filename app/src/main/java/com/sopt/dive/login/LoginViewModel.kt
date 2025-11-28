package com.sopt.dive.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.util.LoginValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

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

    fun login(
        onSuccess: (userId: Long, username: String) -> Unit,
        onError: (String) -> Unit
    ) {
        val currentState = _uiState.value

        // 입력값 검증
        if (!LoginValidator.isAllValid(currentState.id, currentState.password)) {
            val idError = LoginValidator.validateId(currentState.id)
            val passwordError = LoginValidator.validatePassword(currentState.password)

            onError(idError.ifEmpty { passwordError })
            return
        }

        // API 요청
        viewModelScope.launch {
            try {
                val request = LoginRequestDto(
                    username = currentState.id,
                    password = currentState.password
                )

                // suspend 함수 호출
                val response = authService.login(request)

                if (response.success && response.data != null) {
                    Log.d("Login", "로그인 성공: ${response.data}")
                    onSuccess(response.data.userId, currentState.id)
                } else {
                    Log.e("Login", "로그인 실패: ${response.message}")
                    onError(response.message)
                }
            } catch (e: Exception) {
                Log.e("Login", "Network Error: ${e.message}")
                onError("네트워크 오류가 발생했습니다.")
            }
        }
    }
}