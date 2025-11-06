package com.sopt.dive.signup

import android.widget.Toast
import androidx.annotation.StringRes
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
import com.sopt.dive.util.SignUpValidator
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
            SignUpFormTextField(
                labelRes = R.string.id_label,
                placeholderRes = R.string.id_placeholder,
                value = idText,
                onValueChange = { idText = it },
                validateFunc = SignUpValidator::validateId
            )

            Spacer(Modifier.height(20.dp))

            SignUpFormTextField(
                labelRes = R.string.pw_label,
                placeholderRes = R.string.pw_placeholder,
                value = pwText,
                onValueChange = { pwText = it },
                validateFunc = SignUpValidator::validatePassword
            )

            Spacer(Modifier.height(20.dp))

            SignUpFormTextField(
                labelRes = R.string.nickname_label,
                placeholderRes = R.string.nickname_placeholder,
                value = nicknameText,
                onValueChange = { nicknameText = it },
                validateFunc = SignUpValidator::validateNickname
            )

            Spacer(Modifier.height(20.dp))

            SignUpFormTextField(
                labelRes = R.string.alcohol_capacity_label,
                placeholderRes = R.string.alcohol_placeholder,
                value = drinkText,
                onValueChange = { drinkText = it },
                validateFunc = SignUpValidator::validateDrink
            )
        }

        Spacer(Modifier.weight(1f))

        // 회원가입 버튼
        DiveButton(
            text = stringResource(R.string.sign_up_button),
            onClick = {
                if (SignUpValidator.isAllValid(idText, pwText, nicknameText, drinkText)) {
                    Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    onSignUpSuccess(idText, pwText, nicknameText, drinkText)
                } else {
                    Toast.makeText(context, "회원가입 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Composable
private fun SignUpFormTextField(
    labelRes: Int,
    placeholderRes: Int,
    value: String,
    onValueChange: (String) -> Unit,
    validateFunc: (String) -> String,
    modifier: Modifier = Modifier
) {
    // 실시간으로 에러 메시지 계산
    val errorMessage = if (value.isNotBlank()) {
        validateFunc(value)
    } else {
        ""
    }

    DiveTextField(
        modifier = modifier,
        label = stringResource(labelRes),
        value = value,
        onValueChange = onValueChange,
        placeholder = stringResource(placeholderRes),
        errorMessage = errorMessage
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen(Modifier.fillMaxSize())
    }
}