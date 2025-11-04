package com.sopt.dive.my

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.card.ProfileCard
import com.sopt.dive.component.info.InfoItem
import com.sopt.dive.ui.theme.DiveTheme


@Composable
fun MyScreen(
    paddingValues: PaddingValues,
    userId: String,
    userPw: String,
    nickname: String,
    drink: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 20.dp, vertical = 50.dp),
    )
    {
        // 프로필 카드
        ProfileCard(
            name = nickname,
            description = "37기 안드로이드 YB 입니다!!",
            profileImageRes = R.drawable.profile_image
        )

        Spacer(Modifier.height(50.dp))

        // 사용자 정보 섹션
        UserInfoSection(
            userId = userId,
            userPw = userPw,
            userNickname = nickname,
            userDrink = drink
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
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.padding(30.dp)
    ) {
        // ID
        InfoItem(
            label = "ID",
            value = userId
        )

        InfoItem(
            label = "PW",
            value = userPw
        )

        // NICKNAME
        InfoItem(
            label = "NICKNAME",
            value = userNickname
        )

        // 주량
        InfoItem(
            label = "주량",
            value = if (userDrink.isNotEmpty()) "${userDrink}병" else "-"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyScreenPreview() {
    DiveTheme {
        MyScreen(
            paddingValues = PaddingValues(0.dp),
            userId = "testId",
            userPw = "testPassword",
            nickname = "테스트차민",
            drink = "1"
        )
    }
}