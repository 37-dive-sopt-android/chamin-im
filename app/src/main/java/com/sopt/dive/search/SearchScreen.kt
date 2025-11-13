package com.sopt.dive.search

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.ui.theme.Teel200
import com.sopt.dive.ui.theme.Teel700

enum class CardFace {
    Front, Back
}

enum class FlipDirection {
    Horizontal, Vertical
}

@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    var currentFace by remember { mutableStateOf(CardFace.Front) }
    var flipDirection by remember { mutableStateOf(FlipDirection.Horizontal) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 카드
        FlippableCard(
            currentFace = currentFace,
            flipDirection = flipDirection,
            onClick = {
                currentFace = when (currentFace) {
                    CardFace.Front -> CardFace.Back
                    CardFace.Back -> CardFace.Front
                }
            },
            modifier = Modifier.size(350.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // 버튼 그룹
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    flipDirection = FlipDirection.Horizontal
                    // 바로 뒤집기
                    currentFace = when (currentFace) {
                        CardFace.Front -> CardFace.Back
                        CardFace.Back -> CardFace.Front
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("좌/우 뒤집기")
            }

            Button(
                onClick = {
                    flipDirection = FlipDirection.Vertical
                    // 바로 뒤집기
                    currentFace = when (currentFace) {
                        CardFace.Front -> CardFace.Back
                        CardFace.Back -> CardFace.Front
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("상/하 뒤집기")
            }
        }
    }
}

@Composable
fun FlippableCard(
    currentFace: CardFace,
    flipDirection: FlipDirection,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Transition -> 여러 애니메이션 속성을 동시에 관리
    val transition = updateTransition(
        targetState = currentFace,
        label = "cardFlip"
    )

    // 회전 각도 애니메이션
    val rotation by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 600, easing = LinearEasing)
        },
        label = "rotation"
    ) { face ->
        when (face) {
            CardFace.Front -> 0f
            CardFace.Back -> 180f
        }
    }

    Box(
        modifier = modifier
            .clickable { onClick() }
    ) {
        // 앞면과 뒷면 중 보여줄 카드 결정
        val showFront = when (flipDirection) {
            FlipDirection.Horizontal -> rotation <= 90f
            FlipDirection.Vertical -> rotation <= 90f
        }

        if (showFront) {
            CardFront(
                rotation = rotation,
                flipDirection = flipDirection,
                modifier = Modifier.matchParentSize()
            )
        } else {
            CardBack(
                rotation = rotation,
                flipDirection = flipDirection,
                modifier = Modifier.matchParentSize()
            )
        }
    }
}

@Composable
fun CardFront(
    rotation: Float,
    flipDirection: FlipDirection,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .graphicsLayer {
                when (flipDirection) {
                    FlipDirection.Horizontal -> {
                        rotationY = rotation
                        cameraDistance = 12f * density
                    }
                    FlipDirection.Vertical -> {
                        rotationX = rotation
                        cameraDistance = 12f * density
                    }
                }
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Teel200
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = "Front Image",
                    modifier = Modifier
                        .size(180.dp) ,
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "앞면",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Teel700
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "카드를 클릭하여\n뒤집어보세요!",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun CardBack(
    rotation: Float,
    flipDirection: FlipDirection,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .graphicsLayer {
                when (flipDirection) {
                    FlipDirection.Horizontal -> {
                        rotationY = 180f - rotation
                        cameraDistance = 12f * density
                    }
                    FlipDirection.Vertical -> {
                        rotationX = 180f - rotation
                        cameraDistance = 12f * density
                    }
                }
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Teel700
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chamin_profile_image),
                    contentDescription = "Back Image",
                    modifier = Modifier
                        .size(180.dp) ,
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "뒷면",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "SOPT 37기\nAndroid YB",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        paddingValues = PaddingValues(0.dp)
    )
}