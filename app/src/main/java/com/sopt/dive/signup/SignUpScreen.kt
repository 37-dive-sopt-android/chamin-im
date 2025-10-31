package com.sopt.dive.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.text.DiveTitle
import com.sopt.dive.component.textfield.DiveTextField
import com.sopt.dive.ui.component.button.DiveButton
import com.sopt.dive.ui.theme.DiveTheme


@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onSignUpSuccess: (String, String, String, String) -> Unit = { _, _, _, _ -> }
) {
    val context = LocalContext.current

    // 입력값 상태 관리
    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }
    var nicknameText by remember { mutableStateOf("") }
    var drinkText by remember { mutableStateOf("") }

    // 에러 메시지 상태 관리
    var idError by remember { mutableStateOf("") }
    var pwError by remember { mutableStateOf("") }
    var nicknameError by remember { mutableStateOf("") }
    var drinkError by remember { mutableStateOf("") }

    // 유효성 검사 함수들
    fun isValidId(id: String): Boolean {
        return when {
            id.isEmpty() -> {
                idError = "ID를 입력해주세요."
                false
            }
            id.contains(" ") -> {
                idError = "ID에는 공백을 사용할 수 없습니다."
                false
            }
            id.length !in 6..10 -> {
                idError = "ID는 6 ~ 10글자 사이여야 합니다."
                false
            }
            else -> {
                idError = ""
                true
            }
        }
    }

    fun isValidPassword(pw: String): Boolean {
        return when {
            pw.isEmpty() -> {
                pwError = "비밀번호를 입력해주세요."
                false
            }
            pw.contains(" ") -> {
                pwError = "비밀번호에는 공백을 사용할 수 없습니다."
                false
            }
            pw.length !in 8..12 -> {
                pwError = "비밀번호는 8 ~ 12글자 사이여야 합니다."
                false
            }
            else -> {
                pwError = ""
                true
            }
        }
    }

    fun isValidNickname(nickname: String): Boolean {
        return when {
            nickname.isEmpty() -> {
                nicknameError = "닉네임을 입력해주세요."
                false
            }
            nickname.isBlank() || nickname.contains(" ") -> {
                nicknameError = "닉네임에 공백을 사용할 수 없습니다."
                false
            }
            else -> {
                nicknameError = ""
                true
            }
        }
    }

    fun isValidDrink(drink: String): Boolean {
        return when {
            drink.isEmpty() -> {
                drinkError = "주량을 입력해주세요."
                false
            }
            !drink.all { it.isDigit() } -> {
                drinkError = "숫자만 입력 가능합니다."
                false
            }
            else -> {
                drinkError = ""
                true
            }
        }
    }

    fun validateAll() = isValidId(idText) && isValidPassword(pwText) && isValidNickname(nicknameText) && isValidDrink(drinkText)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 제목
        DiveTitle(text = stringResource(R.string.sign_up_title))

        Spacer(Modifier.height(50.dp))

        // 입력 필드들
        Column {
            // ID 입력
            DiveTextField(
                label = stringResource(R.string.id_label),
                value = idText,
                onValueChange = {
                    idText = it
                    isValidId(it)
                },
                placeholder = stringResource(R.string.id_placeholder),
                errorMessage = idError
            )

            Spacer(Modifier.height(20.dp))

            // 비밀번호 입력
            DiveTextField(
                label = stringResource(R.string.pw_label),
                value = pwText,
                onValueChange = {
                    pwText = it
                    isValidPassword(it)
                },
                placeholder = stringResource(R.string.pw_placeholder),
                errorMessage = pwError
            )

            Spacer(Modifier.height(20.dp))

            // 닉네임 입력
            DiveTextField(
                label = stringResource(R.string.nickname_label),
                value = nicknameText,
                onValueChange = {
                    nicknameText = it
                    isValidNickname(it)
                },
                placeholder = stringResource(R.string.nickname_placeholder),
                errorMessage = nicknameError
            )

            Spacer(Modifier.height(20.dp))

            // 주량 입력
            DiveTextField(
                label = stringResource(R.string.alcohol_capacity_label),
                value = drinkText,
                onValueChange = {
                    drinkText = it
                    isValidDrink(it)
                },
                placeholder = stringResource(R.string.alcohol_placeholder),
                errorMessage = drinkError
            )
        }

        Spacer(Modifier.weight(1f))

        // 회원가입 버튼
        DiveButton(
            text = stringResource(R.string.sign_up_button),
            onClick = {
                if (validateAll()) {
                    Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    onSignUpSuccess(idText, pwText, nicknameText, drinkText)
                } else {
                    Toast.makeText(context, "회원가입 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen(Modifier.fillMaxSize())
    }
}