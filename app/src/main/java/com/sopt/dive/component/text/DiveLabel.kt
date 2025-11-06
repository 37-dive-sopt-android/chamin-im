package com.sopt.dive.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun DiveLabel(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 25.sp
) {
    Text(
        text = text,
        fontSize = fontSize,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DiveLabelPreview() {
    DiveLabel("hello")
}