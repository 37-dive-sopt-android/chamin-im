package com.sopt.dive.my

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.sopt.dive.R
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.dto.response.BaseResponse
import com.sopt.dive.data.dto.response.ProfileResponseDto
import com.sopt.dive.my.data.UserProfile
import com.sopt.dive.navigation.MyPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _uiState = MutableStateFlow(MyUiState())
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        val myPageRoute = savedStateHandle.toRoute<MyPage>()
        val userId = myPageRoute.userId.toLongOrNull()

        if (userId == null) {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    error = "유효하지 않은 사용자 ID입니다."
                )
            }
            return
        }

        _uiState.update { it.copy(isLoading = true) }

        authService.getUserInfo(userId).enqueue(object : Callback<BaseResponse<ProfileResponseDto>> {
            override fun onResponse(
                call: Call<BaseResponse<ProfileResponseDto>>,
                response: Response<BaseResponse<ProfileResponseDto>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true && body.data != null) {
                        Log.d("MyScreen", "사용자 정보 조회 성공")
                        _uiState.update { currentState ->
                            currentState.copy(
                                userProfile = UserProfile(
                                    id = body.data.id,
                                    username = body.data.userName,
                                    name = body.data.name,
                                    email = body.data.email,
                                    age = body.data.age.toString(),
                                    description = "37기 안드로이드 YB 입니다!!",
                                    profileImageRes = R.drawable.profile_image,
                                ),
                                isLoading = false,
                                error = null
                            )
                        }
                    } else {
                        Log.e("MyScreen", "사용자 정보 조회 실패: ${body?.message}")
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                error = body?.message ?: "사용자 정보를 불러올 수 없습니다."
                            )
                        }
                    }
                } else {
                    val errorMessage = when (response.code()) {
                        400 -> "잘못된 요청입니다."
                        403 -> "탈퇴한 사용자입니다."
                        404 -> "사용자를 찾을 수 없습니다."
                        else -> "사용자 정보를 불러올 수 없습니다."
                    }
                    Log.e("MyScreen", "Response Error: ${response.code()} - ${response.message()}")
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = errorMessage
                        )
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<ProfileResponseDto>>, t: Throwable) {
                Log.e("MyScreen", "Network Error: ${t.message}")
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = "네트워크 오류가 발생했습니다."
                    )
                }
            }
        })
    }
}