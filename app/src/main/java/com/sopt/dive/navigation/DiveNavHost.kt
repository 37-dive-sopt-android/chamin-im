package com.sopt.dive.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    paddingValues: PaddingValues,
    userId: String,
    userPw: String,
    userNickname: String,
    userEmail: String,
    userAge: String,
    onUserInfoChanged: (String, String, String, String, String) -> Unit
) {
    // 회원가입 정보를 저장할 상태
    var signUpId by remember { mutableStateOf("") }
    var signUpPw by remember { mutableStateOf("") }
    var signUpNickname by remember { mutableStateOf("") }
    var signUpEmail by remember { mutableStateOf("") }
    var signUpAge by remember { mutableStateOf("") }


    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                onSignUpClick = {
                    navController.navigate(SignUp)
                },
                onLoginSuccess = { userId, nickname ->
                    // 로그인 성공 시 사용자 정보 업데이트 및 Home으로 이동
                    onUserInfoChanged(signUpId, signUpPw, nickname, signUpEmail, signUpAge)
                    navController.navigate(
                        Home(
                            userId = userId.toString(),
                            nickname = nickname
                        )
                    ) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }

        composable<SignUp> {
            SignUpScreen(
                onSignUpSuccess = { id, pw, nickname, email, age ->
                    // 회원가입 정보 저장
                    signUpId = id
                    signUpPw = pw
                    signUpNickname = nickname
                    signUpEmail = email
                    signUpAge = age

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
                paddingValues = paddingValues,
                userId = userId,
                userPw = userPw,
                nickname = userNickname,
                email = userEmail,
                age = userAge,
            )
        }
    }
}