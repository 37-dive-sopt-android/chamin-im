package com.sopt.dive.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    paddingValues: PaddingValues,
    userId: String,
    userPw: String,
    userNickname: String,
    userDrink: String,
    onUserInfoChanged: (String, String, String, String) -> Unit
) {
    val context = LocalContext.current

    // 회원가입 정보를 저장할 상태
    var signUpId by remember { mutableStateOf("") }
    var signUpPw by remember { mutableStateOf("") }
    var signUpNickname by remember { mutableStateOf("") }
    var signUpDrink by remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                signUpId = signUpId,
                signUpPw = signUpPw,
                onSignUpClick = {
                    navController.navigate(SignUp)
                },
                onLoginClick = { inputId, inputPw ->
                    when {
                        inputId == signUpId && inputPw == signUpPw -> {
                            Toast.makeText(
                                context,
                                "로그인 성공! ${signUpNickname}님 환영합니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            onUserInfoChanged(signUpId, signUpPw, signUpNickname, signUpDrink)
                            navController.navigate(Home) {
                                popUpTo(Login) { inclusive = true }
                            }
                        }

                        signUpId.isEmpty() -> {
                            Toast.makeText(
                                context,
                                "먼저 회원가입을 해주세요.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            Toast.makeText(
                                context,
                                "아이디 또는 비밀번호가 일치하지 않습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            )
        }

        composable<SignUp> {
            SignUpScreen(
                onSignUpSuccess = { id, pw, nickname, drink ->
                    // 회원가입 정보 저장
                    signUpId = id
                    signUpPw = pw
                    signUpNickname = nickname
                    signUpDrink = drink

                    Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()

                    navController.popBackStack()
                }
            )
        }

        composable<Home> {
            HomeScreen(
                paddingValues = paddingValues,
                userId = userId,
                nickname = userNickname
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
                drink = userDrink
            )
        }
    }
}