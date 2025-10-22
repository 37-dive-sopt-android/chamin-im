package com.sopt.dive.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.MainActivity
import com.sopt.dive.R
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
                    Login(
                        modifier = Modifier.padding(innerPadding).fillMaxSize(),
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





@Composable
fun Login(modifier: Modifier = Modifier,
             signUpId: String = "",
             signUpPw: String = "",
             signUpNickname: String = "",
             signUpDrink : String = "",
             onSignUpClick: ()-> Unit = {},
          onLoginSuccess: ()-> Unit = {},
          onNeedSignUp:()-> Unit = {},
          onLoginFailure:()-> Unit = {}
)
{
    val context = LocalContext.current

    var idText by remember(signUpId) { mutableStateOf("") }
    var pwText by remember(signUpPw) { mutableStateOf("") }

    // 로그인 유효성
    fun isLoginSuccessful(): Boolean {
        return idText == signUpId &&
                pwText == signUpPw &&
                signUpId.isNotEmpty()
    }

    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = stringResource(R.string.welcome_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(50.dp))

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = stringResource(R.string.id_label),
            )

            TextField(
                value =  idText,
                onValueChange = { idText = it },
                Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.id_placeholder)) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,      // 포커스 시
                    unfocusedContainerColor = Color.Transparent,    // 포커스 해제 시
                    disabledContainerColor = Color.Transparent,     // 비활성 시
                )
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.password_label),
            )

            TextField(
                value =  pwText,
                onValueChange = { pwText = it },
                Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.pw_placeholder)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,      // 포커스 시
                    unfocusedContainerColor = Color.Transparent,    // 포커스 해제 시
                    disabledContainerColor = Color.Transparent,     // 비활성 시
                )
            )
        }

        Spacer(Modifier.weight(1f))  // 남은 공간을 모두 차지

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = {
                    if (isLoginSuccessful()) {
                        onLoginSuccess()
                    } else if (signUpId.isEmpty()) {
                        onNeedSignUp()
                    } else {
                        onLoginFailure()
                    }
                },
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),


                ) {
                Text(stringResource(R.string.login_button), color = Color.White)
            }

            Text(
                text = stringResource(R.string.sign_up_link),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(onClick = onSignUpClick)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Login(Modifier.fillMaxSize())
}