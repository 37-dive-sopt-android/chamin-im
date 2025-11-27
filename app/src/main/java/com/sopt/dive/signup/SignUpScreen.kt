package com.sopt.dive.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.component.text.DiveTitle
import com.sopt.dive.component.textfield.DiveTextField
import com.sopt.dive.ui.component.button.DiveButton
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun SignUpRoute(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel(),
    navigateToLogin: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignUpScreen(
        modifier = modifier,
        id = uiState.id,
        password = uiState.password,
        nickname = uiState.nickname,
        email = uiState.email,
        age = uiState.age,
        idError = uiState.idError,
        passwordError = uiState.passwordError,
        nicknameError = uiState.nicknameError,
        emailError = uiState.emailError,
        ageError = uiState.ageError,
        onIdChange = viewModel::updateId,
        onPasswordChange = viewModel::updatePassword,
        onNicknameChange = viewModel::updateNickname,
        onEmailChange = viewModel::updateEmail,
        onAgeChange = viewModel::updateAge,
        onSignUpClick = {
            viewModel.signUp(
                onSuccess = {
                    Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                },
                onError = { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            )
        }
    )
}

@Composable
private fun SignUpScreen(
    modifier: Modifier = Modifier,
    id: String,
    password: String,
    nickname: String,
    email: String,
    age: String,
    idError: String,
    passwordError: String,
    nicknameError: String,
    emailError: String,
    ageError: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
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
            DiveTextField(
                label = stringResource(R.string.id_label),
                placeholder = stringResource(R.string.id_placeholder),
                value = id,
                onValueChange = onIdChange,
                errorMessage = idError
            )

            Spacer(Modifier.height(20.dp))

            DiveTextField(
                label = stringResource(R.string.pw_label),
                value = password,
                onValueChange = onPasswordChange,
                placeholder = stringResource(R.string.pw_placeholder),
                errorMessage = passwordError
            )

            Spacer(Modifier.height(20.dp))

            DiveTextField(
                label = stringResource(R.string.nickname_label),
                value = nickname,
                onValueChange = onNicknameChange,
                placeholder = stringResource(R.string.nickname_placeholder),
                errorMessage = nicknameError
            )

            Spacer(Modifier.height(20.dp))

            DiveTextField(
                label = stringResource(R.string.email_label),
                value = email,
                onValueChange = onEmailChange,
                placeholder = stringResource(R.string.email_placeholder),
                errorMessage = emailError
            )

            Spacer(Modifier.height(20.dp))

            DiveTextField(
                label = stringResource(R.string.age_label),
                value = age,
                onValueChange = onAgeChange,
                placeholder = stringResource(R.string.age_placeholder),
                errorMessage = ageError
            )
        }

        Spacer(Modifier.weight(1f))

        // 회원가입 버튼
        DiveButton(
            text = stringResource(R.string.sign_up_button),
            onClick = onSignUpClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen(
            id = "",
            password = "",
            nickname = "",
            email = "",
            age = "",
            idError = "",
            passwordError = "",
            nicknameError = "",
            emailError = "",
            ageError = "",
            onIdChange = {},
            onPasswordChange = {},
            onNicknameChange = {},
            onEmailChange = {},
            onAgeChange = {},
            onSignUpClick = {}
        )
    }
}