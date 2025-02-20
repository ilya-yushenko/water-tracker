package com.yushenko.watertracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorBlack
import com.yushenko.watertracker.theme.ColorBlue
import com.yushenko.watertracker.theme.ColorBlueWhite
import com.yushenko.watertracker.theme.ColorWhite
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Res

@Composable
fun HydrationItem(
    label: String,
    iconRes: DrawableResource,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .background(if (isSelected) ColorBlue else ColorBlueWhite)
                .size(56.dp)
                .clickable(onClick = onClick)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = "Drink",
                tint = if (isSelected) ColorWhite else ColorBlue,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(28.dp)
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            color = ColorBlack,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
        )
    }
}