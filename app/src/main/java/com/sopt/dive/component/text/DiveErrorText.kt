package com.sopt.dive.component.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DiveErrorText(
    errorMessage: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 12.sp
){
    if (errorMessage.isNotEmpty()) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = fontSize,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DiveErrorTextPreview() {
    DiveErrorText("오류입니다.")
}