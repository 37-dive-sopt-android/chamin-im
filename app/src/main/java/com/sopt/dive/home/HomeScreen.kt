package com.sopt.dive.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.card.CommentCard
import com.sopt.dive.component.card.ProfileCard

data class Comment(
    val name: String,
    val img: Int,
    val comments: String,
)

val comments = listOf(
    Comment(
        name = "엄마",
        img = R.drawable.profile_image,
        comments = "화이팅!"
    ),
    Comment(
        name = "아빠",
        img = R.drawable.profile_image,
        comments = "좋은 하루~~"
    ),
    Comment(
        name = "가람",
        img = R.drawable.profile_image,
        comments = "날씨 좋네요"
    ),
    Comment(
        name = "해솔",
        img = R.drawable.profile_image,
        comments = "겨울엔 붕어빵!"
    ),
    Comment(
        name = "지원",
        img = R.drawable.profile_image,
        comments = "다들 화이팅!!"
    ),
    Comment(
        name = "크크",
        img = R.drawable.profile_image,
        comments = "크크 크롱"
    ),
    Comment(
        name = "뽀로로",
        img = R.drawable.profile_image,
        comments = "다 같이 놀자"
    ),
    Comment(
        name = "더미",
        img = R.drawable.profile_image,
        comments = "더미더미"
    ),
    Comment(
        name = "더미",
        img = R.drawable.profile_image,
        comments = "더미더미"
    ),
    Comment(
        name = "더미",
        img = R.drawable.profile_image,
        comments = "더미더미"
    ),
    Comment(
        name = "더미",
        img = R.drawable.profile_image,
        comments = "더미더미"
    ),
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 프로필 카드
        item {
            ProfileCard(
                name = "임차민",
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
                name = comment.name,
                comment = comment.comments,
                profileImageRes = comment.img
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(Modifier.fillMaxSize())
}