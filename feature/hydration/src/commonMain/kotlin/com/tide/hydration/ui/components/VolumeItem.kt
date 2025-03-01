package com.tide.hydration.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette

data class VolumeModel(val volume: Int)

@Composable
fun VolumeItem(
    model: VolumeModel,
    onClick: (VolumeModel) -> Unit
) {
    val colors = appColorPalette()

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(colors.blue10)
            .size(height = 30.dp, width = 85.dp)
            .clickable(onClick = { onClick(model) }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${model.volume} ml",
            fontSize = 16.sp,
            color = colors.blue,
            fontFamily = AppTypography.medium()
        )
    }
}