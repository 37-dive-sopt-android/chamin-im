package com.sopt.dive.navigation

import kotlinx.serialization.Serializable

interface Route
interface MainTabRoute : Route

// 이외 (바텀 네비게이션 X)
@Serializable
data object Login : Route

@Serializable
data object SignUp : Route

// 메인 탭 (바텀 네비게이션 O)
@Serializable
data class Home(
    val userId: String ="",
    val nickname: String =""
) : MainTabRoute

@Serializable
data object Search : MainTabRoute

@Serializable
data object MyPage : MainTabRoute