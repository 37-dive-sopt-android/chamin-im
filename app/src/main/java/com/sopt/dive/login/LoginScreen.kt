package com.sopt.dive.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.component.text.DiveTitle
import com.sopt.dive.component.textfield.DiveTextField
import com.sopt.dive.ui.component.button.DiveButton

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    navigateToSignUp: () -> Unit,
    navigateToHome: (userId: Long, username: String) -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    LaunchedEffect(loginState) {
        when (val state = loginState) {
            is LoginState.Success -> {
                Toast.makeText(
                    context,
                    "로그인 성공! ${state.username}님 환영합니다.",
                    Toast.LENGTH_SHORT
                ).show()
                navigateToHome(state.userId, state.username)
                viewModel.resetLoginState()
            }
            is LoginState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                viewModel.resetLoginState()
            }
            LoginState.Idle -> Unit
        }
    }

    LoginScreen(
        modifier = modifier,
        id = uiState.id,
        password = uiState.password,
        idError = uiState.idError,
        passwordError = uiState.passwordError,
        onIdChange = viewModel::updateId,
        onPasswordChange = viewModel::updatePassword,
        onSignUpClick = navigateToSignUp,
        onLoginClick = { viewModel.login()}
    )
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    id: String,
    password: String,
    idError: String,
    passwordError: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        DiveTitle(text = stringResource(R.string.welcome_title))

        Spacer(Modifier.height(50.dp))

        Column {
            DiveTextField(
                label = stringResource(R.string.id_label),
                value = id,
                onValueChange = onIdChange,
                placeholder = stringResource(R.string.id_placeholder),
                errorMessage = idError
            )

            Spacer(Modifier.height(20.dp))

            DiveTextField(
                label = stringResource(R.string.password_label),
                value = password,
                onValueChange = onPasswordChange,
                placeholder = stringResource(R.string.pw_placeholder),
                errorMessage = passwordError,
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Spacer(Modifier.weight(1f))

        DiveButton(
            text = stringResource(R.string.login_button),
            onClick = onLoginClick
        )

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
private fun LoginScreenPreview() {
    LoginScreen(
        id = "",
        password = "",
        idError = "",
        passwordError = "",
        onIdChange = {},
        onPasswordChange = {},
        onSignUpClick = {},
        onLoginClick = {}
    )
}