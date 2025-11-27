package com.sopt.dive.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.util.SignUpValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

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

    fun signUp(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val currentState = _uiState.value

        // 유효성 검사
        if (!SignUpValidator.isAllValid(
                currentState.id,
                currentState.password,
                currentState.nickname,
                currentState.email,
                currentState.age
            )) {
            onError("입력 정보를 확인해주세요.")
            return
        }

        val ageInt = currentState.age.toIntOrNull()
        if (ageInt == null || ageInt <= 0) {
            Log.e("SignUp", "나이 변환 실패 또는 0 이하: age=$ageInt")
            onError("나이를 올바르게 입력해주세요.")
            return
        }

        // API 요청
        val request = SignUpRequestDto(
            username = currentState.id,
            password = currentState.password,
            name = currentState.nickname,
            email = currentState.email,
            age = ageInt
        )

        authService.signUp(request).enqueue(object : Callback<BaseResponse<Unit>> {
            override fun onResponse(
                call: Call<BaseResponse<Unit>>,
                response: Response<BaseResponse<Unit>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true && body.data != null) {
                        Log.d("SignUp", "회원가입 성공: ${body.data}")
                        onSuccess()
                    } else {
                        Log.e("SignUp", "회원가입 실패: ${body?.message}")
                        onError(body?.message ?: "회원가입에 실패했습니다.")
                    }
                } else {
                    val errorMessage = when (response.code()) {
                        400 -> "요청 값이 유효하지 않습니다."
                        409 -> "이미 존재하는 사용자명입니다."
                        415 -> "지원하지 않는 콘텐츠 타입입니다."
                        else -> "회원가입에 실패했습니다."
                    }
                    Log.e("SignUp", "Response Error: ${response.code()} - ${response.message()}")
                    onError(errorMessage)
                }
            }

            override fun onFailure(call: Call<BaseResponse<Unit>>, t: Throwable) {
                Log.e("SignUp", "Network Error: ${t.message}")
                onError("네트워크 오류가 발생했습니다.")
            }
        })
    }
}