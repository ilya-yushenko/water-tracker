package com.yushenko.watertracker.ui.components

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorBlue
import com.yushenko.watertracker.theme.ColorBlue10
import org.jetbrains.compose.resources.Font
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Res

data class VolumeModel(val volume: Int)

@Composable
fun VolumeItem(
    model: VolumeModel,
    onClick: (VolumeModel) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(ColorBlue10)
            .size(height = 30.dp, width = 85.dp)
            .clickable(onClick = { onClick(model) }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${model.volume} ml",
            fontSize = 16.sp,
            color = ColorBlue,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium))
        )
    }
}