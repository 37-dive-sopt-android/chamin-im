package com.sopt.dive.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.component.text.DiveErrorText
import com.sopt.dive.component.text.DiveLabel
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun DiveTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    errorMessage: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true
) {
    Column(modifier = modifier) {
        DiveLabel(text = label)

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            singleLine = singleLine,
            isError = errorMessage.isNotEmpty(),
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        )

        DiveErrorText(
            errorMessage = errorMessage,
            modifier = Modifier.padding(start = 4.dp, top = 2.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DiveTextFieldMultiplePreview() {
    DiveTheme {
        var id by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var nickname by remember { mutableStateOf("test") }

        Column(modifier = Modifier.padding(16.dp)) {
            DiveTextField(
                label = "ID",
                value = id,
                onValueChange = { id = it },
                placeholder = "아이디를 입력하세요",
                errorMessage = "ID를 입력해주세요."
            )

            DiveTextField(
                label = "비밀번호",
                value = password,
                onValueChange = { password = it },
                placeholder = "비밀번호를 입력하세요",
                errorMessage = "비밀번호를 입력해주세요.",
                visualTransformation = PasswordVisualTransformation(),
            )

            DiveTextField(
                label = "닉네임",
                value = nickname,
                onValueChange = { nickname = it },
                placeholder = "닉네임을 입력하세요",
                errorMessage = "",
            )
        }
    }
}