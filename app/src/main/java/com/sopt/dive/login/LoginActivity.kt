package com.sopt.dive.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sopt.dive.MainActivity
import com.sopt.dive.signup.SignUpActivity
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.KeyStorage

class LoginActivity : ComponentActivity() {
    // 회원가입에서 가져올 변수
    private var signUpId by mutableStateOf("")
    private var signUpPw by mutableStateOf("")
    private var signUpNickname by mutableStateOf("")
    private var signUpDrink by mutableStateOf("")


    val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // 회원가입 성공 시 데이터 받기
            result.data?.let { data ->
                signUpId = data.getStringExtra(KeyStorage.ID) ?: ""
                signUpPw = data.getStringExtra(KeyStorage.PW) ?: ""
                signUpNickname = data.getStringExtra(KeyStorage.NICKNAME) ?: ""
                signUpDrink = data.getStringExtra(KeyStorage.DRINK) ?: ""
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        signUpId = signUpId,
                        signUpPw = signUpPw,
                        signUpNickname = signUpNickname,
                        signUpDrink = signUpDrink,
                        onSignUpClick = {
                            val intent = Intent(this, SignUpActivity::class.java)
                            signUpLauncher.launch(intent)
                        },
                        onLoginSuccess = {
                            Toast.makeText(
                                this,
                                "로그인 성공! ${signUpNickname}님 환영합니다.",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(this, MainActivity::class.java).apply {
                                putExtra(KeyStorage.ID, signUpId)
                                putExtra(KeyStorage.PW, signUpPw)
                                putExtra(KeyStorage.NICKNAME, signUpNickname)
                                putExtra(KeyStorage.DRINK, signUpDrink)
                            }
                            startActivity(intent)
                            finish()
                        },
                        onNeedSignUp = {
                            Toast.makeText(
                                this,
                                "먼저 회원가입을 해주세요.",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onLoginFailure = {
                            Toast.makeText(
                                this,
                                "아이디 또는 비밀번호가 일치하지 않습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }
}