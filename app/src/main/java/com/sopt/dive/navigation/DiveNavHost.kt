package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.home.HomeScreen
import com.sopt.dive.login.LoginRoute
import com.sopt.dive.my.MyScreen
import com.sopt.dive.search.SearchScreen
import com.sopt.dive.signup.SignUpRoute

@Composable
fun DiveNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginRoute(
                navigateToSignUp = {
                    navController.navigate(SignUp)
                },
                navigateToHome = { userId, username ->
                    navController.navigate(
                        Home(
                            userId = userId.toString(),
                            nickname = username
                        )
                    ) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }

        composable<SignUp> {
            SignUpRoute(
                navigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable<Home> {
            HomeScreen(
                paddingValues = paddingValues
            )
        }

        composable<Search> {
            SearchScreen(
                paddingValues = paddingValues
            )
        }

        composable<MyPage> {
            MyScreen(
                paddingValues = paddingValues
            )
        }
    }
}