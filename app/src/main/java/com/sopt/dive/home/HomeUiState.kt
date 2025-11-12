package com.sopt.dive.home

import com.sopt.dive.home.data.Comment
import com.sopt.dive.home.data.Profile

data class HomeUiState(
    val profile: Profile = Profile("","",0),
    val comments: List<Comment> = emptyList()
)