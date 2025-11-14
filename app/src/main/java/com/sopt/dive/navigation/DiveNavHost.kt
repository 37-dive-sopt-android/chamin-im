package com.sopt.dive.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.home.HomeScreen
import com.sopt.dive.login.LoginScreen
import com.sopt.dive.my.MyScreen
import com.sopt.dive.search.SearchScreen
import com.sopt.dive.signup.SignUpScreen

@Composable
fun DiveNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                onSignUpClick = {
                    navController.navigate(SignUp)
                },
                onLoginSuccess = { userId, username ->
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
            SignUpScreen(
                onSignUpSuccess = { _, _, _, _, _ ->
                    Toast.makeText(context, "회원가입 성공! 로그인해주세요.", Toast.LENGTH_SHORT).show()
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