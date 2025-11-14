package com.sopt.dive.my

import com.sopt.dive.my.data.UserProfile

data class MyUiState(
    val userProfile: UserProfile = UserProfile(),
    val isLoading: Boolean = false,
    val error: String? = null
)