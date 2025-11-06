package com.sopt.dive.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.card.CommentCard
import com.sopt.dive.component.card.ProfileCard
import com.sopt.dive.home.data.comments
import androidx.compose.foundation.lazy.items
import com.sopt.dive.component.card.CommentData

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    userId: String,
    nickname: String,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 프로필 카드
        item {
            ProfileCard(
                name = nickname,
                description = "37기 안드로이드 YB 입니다!!",
                profileImageRes = R.drawable.chamin_profile_image
            )
        }

        item {
            Spacer(Modifier.height(8.dp))
        }

        // 코멘트 리스트
        items(comments) { comment ->
            CommentCard(
                commentData = CommentData(
                    name = comment.name,
                    comment = comment.comments,
                    profileImageRes = comment.img
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        paddingValues = PaddingValues(0.dp),
        userId = "chamin",
        nickname = "임차민",
        modifier = Modifier.fillMaxSize()
    )
}