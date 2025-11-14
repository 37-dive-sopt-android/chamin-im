package com.sopt.dive.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.dto.request.RequestLoginDto
import com.sopt.dive.data.dto.response.ResponseLoginDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        if (currentState.id.isEmpty()) {
            onError("아이디를 입력해주세요.")
            return
        }
        if (currentState.password.isEmpty()) {
            onError("비밀번호를 입력해주세요.")
            return
        }

        // API 요청
        val request = RequestLoginDto(
            username = currentState.id,
            password = currentState.password
        )

        authService.login(request).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true && body.data != null) {
                        Log.d("Login", "로그인 성공: ${body.data}")
                        // username을 그대로 전달
                        onSuccess(body.data.userId, currentState.id)
                    } else {
                        Log.e("Login", "로그인 실패: ${body?.message}")
                        onError(body?.message ?: "로그인에 실패했습니다.")
                    }
                } else {
                    val errorMessage = when (response.code()) {
                        400 -> "입력 정보가 올바르지 않습니다."
                        401 -> "아이디 또는 비밀번호가 올바르지 않습니다."
                        403 -> "탈퇴한 사용자입니다."
                        404 -> "존재하지 않는 사용자입니다."
                        else -> "로그인에 실패했습니다."
                    }
                    Log.e("Login", "Response Error: ${response.code()} - ${response.message()}")
                    onError(errorMessage)
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                Log.e("Login", "Network Error: ${t.message}")
                onError("네트워크 오류가 발생했습니다.")
            }
        })
    }
}

sealed class LoginResult {
    object Success : LoginResult()
    object NoSignUp : LoginResult()
    object InvalidCredentials : LoginResult()
}