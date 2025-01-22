package com.yushenko.watertracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.yushenko.watertracker.theme.ColorGray
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Res

data class DrinkModel(val label: String, val volume: String, val iconRes: DrawableResource)

@Composable
fun DrinkItem(
    label: String,
    volume: String,
    iconRes: DrawableResource,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 26.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = ColorBlue,
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .background(ColorBlueWhite)
                .padding(16.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            color = ColorBlack,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
        )
        Text(
            text = volume,
            fontSize = 12.sp,
            color = ColorGray,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
        )
    }
}