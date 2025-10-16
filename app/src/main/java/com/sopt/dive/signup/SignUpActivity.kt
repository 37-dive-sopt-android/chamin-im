package com.sopt.dive.signup

import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.login.Greeting
import com.sopt.dive.ui.theme.DiveTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
                    SingUP(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SingUP(Modifier.fillMaxSize())
}

@Composable
fun SingUP(modifier: Modifier = Modifier) {

    // 입력값 상태 관리
    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }
    var nicknameText by remember { mutableStateOf("") }
    var drankText by remember { mutableStateOf("") }

    // 에러 메시지 상태 관리
    var idError by remember { mutableStateOf("") }
    var pwError by remember { mutableStateOf("") }
    var nicknameError by remember { mutableStateOf("") }
    var drankError by remember { mutableStateOf("") }

    fun validateId(id: String): Boolean {
        return when {
            id.isEmpty() -> {
                idError = "ID를 입력해주세요."
                false
            }
            id.contains(" ") -> {
                idError = "ID에는 공백을 사용할 수 없습니다."
                false
            }
            id.length < 6 -> {
                idError = "ID는 6글자 이상이어야 합니다."
                false
            }
            id.length > 10 -> {
                idError = "ID는 10글자 이하여야 합니다."
                false
            }
            else -> {
                idError = ""
                true
            }
        }
    }

    fun validatePw(pw: String): Boolean {
        return when {
            pw.isEmpty() -> {
                pwError = "비밀번호를 입력해주세요."
                false
            }
            pw.contains(" ") -> {
                pwError = "비밀번호에는 공백을 사용할 수 없습니다."
                false
            }
            pw.length < 8 -> {
                pwError = "PW는 8글자 이상이어야 합니다."
                false
            }
            pw.length > 12 -> {
                pwError = "PW는 12글자 이하여야 합니다."
                false
            }
            else -> {
                pwError = ""
                true
            }
        }
    }

    fun validateNickname(nickname: String): Boolean {
        return when {
            nickname.isEmpty() -> {
                nicknameError = "닉네임을 입력해주세요."
                false
            }
            nickname.trim().isEmpty() -> {
                nicknameError = "닉네임 공백은 사용할 수 없습니다."
                false
            }
            nickname.isBlank() -> {
                nicknameError = "닉네임은 한 글자 이상이어야 합니다."
                false
            }
            else -> {
                nicknameError = ""
                true
            }

        }
    }

    fun validateDrink(drink: String): Boolean {
        return when {

            drink.isEmpty() -> {
                drankError = "주량을 입력해주세요."
                false
            }
            !drink.all { it.isDigit() } -> {
                drankError = "숫자만 입력 가능합니다."
                false
            }
            else -> {
                drankError = ""
                true
            }

        }
    }

    fun validateAll(): Boolean {
        val isIdValid = validateId(idText)
        val isPasswordValid = validatePw(pwText)
        val isNicknameValid = validateNickname(nicknameText)
        val isDrinkValid = validateDrink(drankText)

        return isIdValid && isPasswordValid && isNicknameValid && isDrinkValid
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = stringResource(R.string.sign_up_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(50.dp))

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top

        ) {
            Text(
                text = stringResource(R.string.id_label),
                fontSize = 25.sp,
            )

            TextField(
                value = idText,
                onValueChange = {
                    idText = it
                    validateId(it)
                },
                Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.id_placeholder)) },
                singleLine = true,
                isError = idError.isNotEmpty(),  // 에러 상태 표시 // 에러 비어있음 -> false (에러x)
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )

            // 에러 메시지 표시
            if (idError.isNotEmpty()) {
                Text(
                    text = idError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.pw_label),
                fontSize = 25.sp,
            )

            TextField(
                value = pwText,
                onValueChange = {
                    pwText = it
                    validatePw(it)
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.pw_placeholder)) },
                singleLine = true,
                isError = pwError.isNotEmpty(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )

            if (pwError.isNotEmpty()) {
                Text(
                    text = pwError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.nickname_label),
                fontSize = 25.sp,
            )

            TextField(
                value = nicknameText,
                onValueChange = {
                    nicknameText = it
                    validateNickname(it)
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.nickname_placeholder)) },
                singleLine = true,
                isError = nicknameError.isNotEmpty(),

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )

            if (nicknameError.isNotEmpty()) {
                Text(
                    text = nicknameError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.alcohol_capacity_label),
                fontSize = 25.sp,
            )

            TextField(
                value = drankText,
                onValueChange = {
                    drankText = it
                    validateDrink(it)
                                },
                Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.alcohol_placeholder)) },
                singleLine = true,
                isError = drankError.isNotEmpty(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )
            if (drankError.isNotEmpty()) {
                Text(
                    text = drankError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                )
            }
        }

        Spacer(Modifier.weight(1f))  // 남은 공간을 모두 차지

        Button(
            onClick = {
                if (validateAll()) { // 회원가입 성공

                } else {

                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(stringResource(R.string.sign_up_button))
        }
    }
}