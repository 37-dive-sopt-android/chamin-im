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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun CommentCard(
    name: String,
    comment: String,
    profileImageRes: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFE0E0E0))
            .padding(16.dp)
    ) {
        // 프로필 이미지
        Image(
            painter = painterResource(profileImageRes),
            contentDescription = "프로필 이미지",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.width(12.dp))

        // 이름과 코멘트
        Column {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Spacer(Modifier.width(4.dp))

            Text(
                text = comment,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentCardPreview() {
    DiveTheme {
        CommentCard(
            name = "임차민",
            comment = "빨리 안두콩들 보고싶다.",
            profileImageRes = R.drawable.profile_image,
            modifier = Modifier.padding(16.dp)
        )
    }
}