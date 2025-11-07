package com.sopt.dive.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.component.card.CommentCard
import com.sopt.dive.component.card.ProfileCard

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    userId: String,
    nickname: String,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {

    LaunchedEffect(nickname) {
        viewModel.setUserProfile(nickname)
    }

    // 상태 구독
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        uiState.profile?.let { profileData ->
            item {
                ProfileCard(
                    name = profileData.name,
                    description = profileData.description,
                    profileImageRes = profileData.profileImageRes
                )
            }

            item {
                Spacer(Modifier.height(8.dp))
            }
        }
        items(uiState.comments) { comment ->
            CommentCard(commentData = comment)
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