package com.sopt.dive.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.theme.Teel200
import com.sopt.dive.ui.theme.Teel700

@Composable
fun ProfileCard(
    name: String,
    description: String,
    profileImageRes: Int,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Teel200)
            .padding(16.dp)
    ) {
        // 이미지
        Image(
            painter = painterResource(profileImageRes),
            contentDescription = "프로필 이미지",
            modifier = Modifier.size(100.dp)
        )

        Spacer(Modifier.width(20.dp))

        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            // 이름
            Text(
                text = name,
                fontWeight = FontWeight.Bold
            )

            //자기소개
            Text(
                text = description,
                color = Teel700
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileCardPreview() {
    DiveTheme {
        ProfileCard(
            name = "임차민",
            description = "37기 안드로이드 YB 입니다!!",
            profileImageRes = R.drawable.profile_image,
            modifier = Modifier.padding(16.dp)
        )
    }
}