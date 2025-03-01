package com.tide.hydration.ui.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun HydrationItem(
    label: String,
    iconRes: DrawableResource,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val colors = appColorPalette()

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .background(if (isSelected) colors.blue else colors.secondary)
                .size(56.dp)
                .clickable(onClick = onClick)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = "Drink",
                tint = if (isSelected) colors.surface else colors.blue,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(28.dp)
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontFamily = AppTypography.medium(),
        )
    }
}