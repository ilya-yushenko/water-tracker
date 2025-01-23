package com.yushenko.watertracker.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorWhite
import com.yushenko.watertracker.theme.textColor
import org.jetbrains.compose.resources.Font
import watertracker.composeapp.generated.resources.Inter_Bold
import watertracker.composeapp.generated.resources.Inter_SemiBold
import watertracker.composeapp.generated.resources.Res

@Composable
fun TitleScreen(
    text: String,
    color: Color = ColorWhite,
) {
    Text(
        text = text,
        fontSize = 24.sp,
        textAlign = TextAlign.Start,
        fontFamily = FontFamily(Font(Res.font.Inter_SemiBold)),
        color = color,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 62.dp)
            .padding(horizontal = 16.dp)
    )
}