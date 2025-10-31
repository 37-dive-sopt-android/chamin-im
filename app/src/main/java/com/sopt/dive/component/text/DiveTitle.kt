package com.sopt.dive.component.text

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.sopt.dive.R

@Composable
fun DiveTitle(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 30.sp
){
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun DiveTitlePreview() {
    DiveTitle("hello")
}