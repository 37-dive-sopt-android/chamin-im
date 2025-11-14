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
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel(),
    onSignUpSuccess: (String, String, String, String, String) -> Unit = { _, _, _, _, _ -> }
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                value = uiState.id,
                onValueChange = viewModel::updateId,
                errorMessage = uiState.idError
            )

            Spacer(Modifier.height(20.dp))

            DiveTextField(
                label = stringResource(R.string.pw_label),
                value = uiState.password,
                onValueChange = viewModel::updatePassword,
                placeholder = stringResource(R.string.pw_placeholder),
                errorMessage = uiState.passwordError
            )

            Spacer(Modifier.height(20.dp))

            DiveTextField(
                label = stringResource(R.string.nickname_label),
                value = uiState.nickname,
                onValueChange = viewModel::updateNickname,
                placeholder = stringResource(R.string.nickname_placeholder),
                errorMessage = uiState.nicknameError
            )

            Spacer(Modifier.height(20.dp))

            DiveTextField(
                label = stringResource(R.string.email_label),
                value = uiState.email,
                onValueChange = viewModel::updateEmail,
                placeholder = stringResource(R.string.email_placeholder),
                errorMessage = uiState.emailError
            )

            Spacer(Modifier.height(20.dp))

            DiveTextField(
                label = stringResource(R.string.age_label),
                value = uiState.age,
                onValueChange = viewModel::updateAge,
                placeholder = stringResource(R.string.age_placeholder),
                errorMessage = uiState.ageError
            )
        }

        Spacer(Modifier.weight(1f))

        // 회원가입 버튼
        DiveButton(
            text = stringResource(R.string.sign_up_button),
            onClick = {
                viewModel.signUp(
                    onSuccess = {
                        Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        onSignUpSuccess(
                            uiState.id,
                            uiState.password,
                            uiState.nickname,
                            uiState.email,
                            uiState.age
                        )
                    },
                    onError = { message ->
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                )
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