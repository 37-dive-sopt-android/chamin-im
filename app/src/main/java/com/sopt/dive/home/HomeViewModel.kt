package com.sopt.dive.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.sopt.dive.R
import com.sopt.dive.home.data.Comment
import com.sopt.dive.home.data.CommentDummyData
import com.sopt.dive.home.data.Profile
import com.sopt.dive.navigation.Home
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    // 프로필 데이터를 ViewModel에서 관리
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
        loadComments()
    }

    private fun loadUserProfile(){
        val homeRoute = savedStateHandle.toRoute<Home>()

        _uiState.update { currentState ->
            currentState.copy(
                profile = Profile(
                    name = homeRoute.nickname,
                    description = "37기 안드로이드 YB 입니다!!!",
                    profileImageRes = R.drawable.chamin_profile_image,
                )
            )
        }
    }

    private fun loadComments(){
        _uiState.update { currentState ->
            currentState.copy(
                comments = CommentDummyData.comments
            )
        }
    }
}