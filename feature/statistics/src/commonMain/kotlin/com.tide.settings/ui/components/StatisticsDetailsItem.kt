package com.tide.settings.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun StatisticsDetailsItem(
    title: String,
    data: String,
    iconRes: DrawableResource,
    modifier: Modifier = Modifier
) {
    val colors = appColorPalette()

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(colors.surface)
            .height(100.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = colors.blue,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            color = colors.gray,
            fontFamily = AppTypography.medium(),
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = data,
            fontSize = 18.sp,
            fontFamily = AppTypography.semiBold(),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}