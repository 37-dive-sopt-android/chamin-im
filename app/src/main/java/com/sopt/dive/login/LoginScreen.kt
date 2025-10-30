package com.sopt.dive.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.text.DiveTitle
import com.sopt.dive.component.textfield.DiveTextField
import com.sopt.dive.ui.component.button.DiveButton


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    signUpId: String = "",
    signUpPw: String = "",
    signUpNickname: String = "",
    signUpDrink: String = "",
    onSignUpClick: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    onNeedSignUp: () -> Unit = {},
    onLoginFailure: () -> Unit = {}
) {
    var idText by remember(signUpId) { mutableStateOf("") }
    var pwText by remember(signUpPw) { mutableStateOf("") }

    // 로그인 유효성
    fun isLoginSuccessful(): Boolean {
        return idText == signUpId &&
                pwText == signUpPw &&
                signUpId.isNotEmpty()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        // 제목
        DiveTitle(text = stringResource(R.string.welcome_title))

        Spacer(Modifier.height(50.dp))

        // 입력 필드들
        Column {
            // ID 입력
            DiveTextField(
                label = stringResource(R.string.id_label),
                value = idText,
                onValueChange = { idText = it },
                placeholder = stringResource(R.string.id_placeholder),
                errorMessage = ""
            )

            Spacer(Modifier.height(20.dp))

            // 비밀번호 입력
            DiveTextField(
                label = stringResource(R.string.password_label),
                value = pwText,
                onValueChange = { pwText = it },
                placeholder = stringResource(R.string.pw_placeholder),
                errorMessage = "",
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Spacer(Modifier.weight(1f))  // 남은 공간을 모두 차지

        // 로그인 버튼
        DiveButton(
            text = stringResource(R.string.login_button),
            onClick = {
                when {
                    isLoginSuccessful() -> onLoginSuccess()
                    signUpId.isEmpty() -> onNeedSignUp()
                    else -> onLoginFailure()
                }
            }
        )

        // 회원가입 링크
        Text(
            text = stringResource(R.string.sign_up_link),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(16.dp)
                .clickable(onClick = onSignUpClick)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LoginScreen(Modifier.fillMaxSize())
}