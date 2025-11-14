package com.sopt.dive.my

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.component.card.ProfileCard
import com.sopt.dive.component.info.InfoItem
import com.sopt.dive.ui.theme.DiveTheme


@Composable
fun MyScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = viewModel()
) {
    // 상태 구독
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        when {
            uiState.isLoading -> {
                // TODO: 로딩
            }
            uiState.error != null -> {
                Text(
                    text = uiState.error ?: "오류가 발생했습니다.",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
            else -> {
                MyScreenContent(uiState = uiState)
            }
        }
    }
}

@Composable
private fun MyScreenContent(
    uiState: MyUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 50.dp)
    )
    {
        // 프로필 카드
        ProfileCard(
            name = uiState.userProfile.name,
            description = uiState.userProfile.description,
            profileImageRes = uiState.userProfile.profileImageRes
        )

        Spacer(Modifier.height(50.dp))

        // 사용자 정보 섹션
        UserInfoSection(
            username = uiState.userProfile.username,
            name = uiState.userProfile.name,
            email = uiState.userProfile.email,
            age = uiState.userProfile.age
        )
    }
}

@Composable
private fun UserInfoSection(
    username: String,
    name: String,
    email: String,
    age: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.padding(30.dp)
    ) {
        // USERNAME - ID - 네이밍 애매..
        InfoItem(
            label = "USERNAME",
            value = username
        )

        // NAME (nickname)
        InfoItem(
            label = "NAME",
            value = name
        )

        // Email
        InfoItem(
            label = "EMAIL",
            value = email
        )

        // Age
        InfoItem(
            label = "AGE",
            value = age
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyScreenPreview() {
    DiveTheme {
        MyScreen(
            paddingValues = PaddingValues(0.dp)
        )
    }
}