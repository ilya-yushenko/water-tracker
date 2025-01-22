package com.yushenko.watertracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorBlue
import com.yushenko.watertracker.theme.ColorBlue10
import org.jetbrains.compose.resources.Font
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Res

data class VolumeModel(val volume: Float)

@Composable
fun VolumeItem(
    model: VolumeModel,
    onClick: (VolumeModel) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(end = 8.dp)
            .clickable(
                onClick = { onClick(model) },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    ) {
        Text(
            text = "${model.volume.toInt()}ml",
            fontSize = 16.sp,
            color = ColorBlue,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(ColorBlue10)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}