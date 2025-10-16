package com.sopt.dive.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.signup.SignUpActivity
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable fun Preview() {
    Greeting(Modifier.fillMaxSize())
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    val context = LocalContext.current

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

            var idText by remember { mutableStateOf("") }
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

            var pwText by remember { mutableStateOf("") }
            TextField(
                value =  pwText,
                onValueChange = { pwText = it },
                Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.pw_placeholder)) },
                singleLine = true,
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
                onClick = { /* 클릭 시 수행될 동작 */ },
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
                    .clickable(onClick = {

                        val intent = Intent(context, SignUpActivity::class.java).apply {
//                            Intent.setFlags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        context.startActivity(intent)
                    })
            )

        }

    }

}