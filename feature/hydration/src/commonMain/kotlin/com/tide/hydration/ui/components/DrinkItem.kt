package com.tide.hydration.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class DrinkModel(val label: String, val volume: String, val iconRes: DrawableResource)

@Composable
fun DrinkItem(
    label: String,
    volume: String,
    iconRes: DrawableResource,
    onClick: () -> Unit
) {
    val colors = appColorPalette()

    Column(
        modifier = Modifier
            .padding(vertical = 26.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .background(colors.secondary)
                .size(64.dp)
                .clickable(onClick = onClick)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = "Drink",
                tint = colors.blue,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontFamily = AppTypography.medium(),
        )
        Text(
            text = volume,
            fontSize = 12.sp,
            color = colors.gray,
            textAlign = TextAlign.Center,
            fontFamily = AppTypography.medium(),
        )
    }
}