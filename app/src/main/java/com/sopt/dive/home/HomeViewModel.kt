package com.sopt.dive.home

import androidx.lifecycle.ViewModel
import com.sopt.dive.R
import com.sopt.dive.home.data.Comment
import com.sopt.dive.home.data.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    // 프로필 데이터를 ViewModel에서 관리
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun setUserProfile(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                profile = Profile(
                    name = name,
                    description = "37기 안드로이드 YB 입니다!!",
                    profileImageRes = R.drawable.chamin_profile_image
                )
            )
        }
    }

    init {
        loadComments()
    }

    private fun loadComments(){
        _uiState.update { currentState ->
            currentState.copy(
                comments = listOf(
                    Comment(
                        name = "엄마요",
                        img = R.drawable.profile_image,
                        comments = "화이팅!"
                    ),
                    Comment(
                        name = "아빠",
                        img = R.drawable.profile_image,
                        comments = "좋은 하루~~"
                    ),
                    Comment(
                        name = "가람",
                        img = R.drawable.profile_image,
                        comments = "날씨 좋네요"
                    ),
                    Comment(
                        name = "해솔",
                        img = R.drawable.profile_image,
                        comments = "겨울엔 붕어빵!"
                    ),
                    Comment(
                        name = "지원",
                        img = R.drawable.profile_image,
                        comments = "다들 화이팅!!"
                    ),
                    Comment(
                        name = "크크",
                        img = R.drawable.profile_image,
                        comments = "크크 크롱"
                    ),
                    Comment(
                        name = "뽀로로",
                        img = R.drawable.profile_image,
                        comments = "다 같이 놀자"
                    ),
                    Comment(
                        name = "더미",
                        img = R.drawable.profile_image,
                        comments = "더미더미"
                    ),
                    Comment(
                        name = "더미",
                        img = R.drawable.profile_image,
                        comments = "더미더미"
                    ),
                    Comment(
                        name = "더미",
                        img = R.drawable.profile_image,
                        comments = "더미더미"
                    ),
                    Comment(
                        name = "더미",
                        img = R.drawable.profile_image,
                        comments = "더미더미"
                    ),
                )
            )
        }
    }
}