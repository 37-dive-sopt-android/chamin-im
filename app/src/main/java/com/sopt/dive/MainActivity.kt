package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.signup.SignUpActivity
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.theme.Teel200
import com.sopt.dive.ui.theme.Teel700
import org.intellij.lang.annotations.JdkConstants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra("id") ?: ""
        val userPw = intent.getStringExtra("pw") ?: ""
        val userNickname = intent.getStringExtra("nickname") ?: ""
        val userDrink = intent.getStringExtra("drink") ?: ""

        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainPage(
                        modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        userId = userId,
                        userPw = userPw,
                        userNickname = userNickname,
                        userDrink = userDrink
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable fun Preview() {
    MainPage(Modifier.fillMaxSize())
}

@Composable
fun MainPage(
    modifier: Modifier = Modifier,
    userId: String = "",
    userPw: String = "",
    userNickname: String = "",
    userDrink: String = ""
) {

    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 50.dp),
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Teel200)
                .padding(16.dp)


        ) {
            // 이미지
            Image(
                painter = painterResource(R.drawable.profile_image),
                contentDescription = "프로필 이미지",
                modifier = Modifier.size(100.dp)
            )

            Spacer(Modifier.width(20.dp))

            Column(
                horizontalAlignment = Alignment.Start,

                ) {

                // 이름
                Text(
                    text = "임차민",
                    fontWeight = FontWeight.Bold
                )


                //자기소개
                Text(
                    text = "37기 안드로이드 YB 입니다!!",
                    color = Teel700
                )

            }

        }

        Spacer(Modifier.height(50.dp))

        Column(
            modifier = Modifier.padding(30.dp)
        ) {
            // ID
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "ID",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = userId,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.height(24.dp))

            // PW
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "PW",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = userPw,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.height(24.dp))

            // NICKNAME
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "NICKNAME",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = userNickname,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.height(24.dp))

            // 주량
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "주량",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = userDrink,
                    color = Color.Gray
                )
            }
        }
    }


}
