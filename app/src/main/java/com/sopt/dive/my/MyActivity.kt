package com.sopt.dive.my

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.card.ProfileCard
import com.sopt.dive.component.info.InfoItem
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.KeyStorage

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra(KeyStorage.ID) ?: ""
        val userPw = intent.getStringExtra(KeyStorage.PW) ?: ""
        val userNickname = intent.getStringExtra(KeyStorage.NICKNAME) ?: ""
        val userDrink = intent.getStringExtra(KeyStorage.DRINK) ?: ""

        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
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



@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    userId: String,
    userPw: String,
    userNickname: String,
    userDrink: String
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 50.dp),
    )
    {
        // 프로필 카드
        ProfileCard(
            name = "임차민",
            description = "37기 안드로이드 YB 입니다!!",
            profileImageRes = R.drawable.profile_image
        )

        Spacer(Modifier.height(50.dp))

        // 사용자 정보 섹션
        UserInfoSection(
            userId = userId,
            userPw = userPw,
            userNickname = userNickname,
            userDrink = userDrink
        )
    }
}

@Composable
private fun UserInfoSection(
    userId: String,
    userPw: String,
    userNickname: String,
    userDrink: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(30.dp)) {
        // ID
        InfoItem(
            label = "ID",
            value = userId
        )

            Spacer(Modifier.height(24.dp))

        // PW
        InfoItem(
            label = "PW",
            value = userPw
        )

        Spacer(Modifier.height(24.dp))

        // NICKNAME
        InfoItem(
            label = "NICKNAME",
            value = userNickname
        )

        Spacer(Modifier.height(24.dp))

        // 주량
        InfoItem(
            label = "주량",
            value = userDrink
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyScreenPreview() {
    DiveTheme {
        MyScreen(
            modifier = Modifier.fillMaxSize(),
            userId = "testId",
            userPw = "testPassword",
            userNickname = "테스트차민",
            userDrink = "1"
        )
    }
}
