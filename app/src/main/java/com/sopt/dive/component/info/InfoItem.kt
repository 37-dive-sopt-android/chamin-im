package com.sopt.dive.component.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun InfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    labelFontSize: TextUnit = 20.sp,
    valueFontSize: TextUnit = 13.sp,
    valueColor: Color = Color.Gray
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(
            text = label,
            fontSize = labelFontSize,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = value,
            fontSize = valueFontSize,
            color = valueColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InfoItemPreview() {
    DiveTheme {
        Column {
            InfoItem(
                label = "ID",
                value = "testUser123"
            )

            Spacer(Modifier.height(24.dp))

            InfoItem(
                label = "NICKNAME",
                value = "테스트차민"
            )
        }
    }
}