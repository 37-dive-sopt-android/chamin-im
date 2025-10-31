package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sopt.dive.home.HomeScreen
import com.sopt.dive.my.MyScreen
import com.sopt.dive.search.SearchScreen

@Composable
fun DiveNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    userId: String,
    userPw: String,
    userNickname: String,
    userDrink: String
) {
    NavHost(
        navController = navController,
        startDestination = Home(userId = userId, nickname = userNickname)
    ) {
        composable<Home> { backStackEntry ->
            val homeRoute = backStackEntry.toRoute<Home>()

            HomeScreen(
                paddingValues = paddingValues,
                userId = homeRoute.userId,
                nickname = homeRoute.nickname
            )
        }

        composable<Search> {
            SearchScreen(
                paddingValues = paddingValues
            )
        }

        composable<MyPage> { backStackEntry ->
            val myPageRoute = backStackEntry.toRoute<MyPage>()

            MyScreen(
                paddingValues = paddingValues,
                userId = myPageRoute.userId,
                userPw = myPageRoute.userPw,
                nickname = myPageRoute.nickname,
                drink = myPageRoute.drink
            )
        }
    }
}