package com.sopt.dive.navigation

import kotlinx.serialization.Serializable

@Serializable
data class Home(
    val userId: String,
    val nickname: String
)

@Serializable
data object Search

@Serializable
data class MyPage(
    val userId: String,
    val userPw: String,
    val nickname: String,
    val drink: String
)