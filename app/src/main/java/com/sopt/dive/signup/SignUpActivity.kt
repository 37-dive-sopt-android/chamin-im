package com.sopt.dive.signup

import android.os.Bundle
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
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable fun Preview() {
    SingUP(Modifier.fillMaxSize())
}

@Composable
fun SingUP(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 50.dp),
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

            var idText by remember{ mutableStateOf("") }
            TextField(
                value = idText,
                onValueChange = { idText = it },
                Modifier.fillMaxWidth(),
                placeholder = {Text(stringResource(R.string.id_placeholder))},
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.pw_label),
                fontSize = 25.sp,
            )

            var pwText by remember{ mutableStateOf("") }
            TextField(
                value = pwText,
                onValueChange = { pwText = it },
                Modifier.fillMaxWidth(),
                placeholder = {Text(stringResource(R.string.pw_placeholder))},
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.nickname_label),
                fontSize = 25.sp,
            )

            var nicknameText by remember{ mutableStateOf("") }
            TextField(
                value = nicknameText,
                onValueChange = { nicknameText = it },
                Modifier.fillMaxWidth(),
                placeholder = {Text(stringResource(R.string.nickname_placeholder))},
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.alcohol_capacity_label),
                fontSize = 25.sp,
            )

            var drankText by remember{ mutableStateOf("") }
            TextField(
                value = drankText,
                onValueChange = { drankText = it },
                Modifier.fillMaxWidth(),
                placeholder = {Text(stringResource(R.string.alcohol_placeholder))},
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )
        }

        Spacer(Modifier.weight(1f))  // 남은 공간을 모두 차지

        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(stringResource(R.string.sign_up_button))
        }
    }
}