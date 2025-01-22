package com.yushenko.watertracker.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.textColor
import org.jetbrains.compose.resources.Font
import watertracker.composeapp.generated.resources.Inter_Bold
import watertracker.composeapp.generated.resources.Res

@Composable
fun TitleScreen(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        fontFamily = FontFamily(Font(Res.font.Inter_Bold)),
        color = textColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp)
            .padding(horizontal = 16.dp)
    )
}