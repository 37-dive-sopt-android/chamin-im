package com.sopt.dive.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.response.LoginData
import com.sopt.dive.util.LoginValidator
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
        if (!LoginValidator.isAllValid(currentState.id, currentState.password)) {
            val idError = LoginValidator.validateId(currentState.id)
            val passwordError = LoginValidator.validatePassword(currentState.password)

            onError(idError.ifEmpty { passwordError })
            return
        }

        // API 요청
        val request = LoginRequestDto(
            username = currentState.id,
            password = currentState.password
        )

        authService.login(request).enqueue(object : Callback<BaseResponse<LoginData>> {
            override fun onResponse(
                call: Call<BaseResponse<LoginData>>,
                response: Response<BaseResponse<LoginData>>
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

            override fun onFailure(call: Call<BaseResponse<LoginData>>, t: Throwable) {
                Log.e("Login", "Network Error: ${t.message}")
                onError("네트워크 오류가 발생했습니다.")
            }
        })
    }
}