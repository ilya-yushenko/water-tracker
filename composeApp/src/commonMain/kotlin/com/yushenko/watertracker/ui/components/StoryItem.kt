package com.yushenko.watertracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import com.yushenko.watertracker.theme.ColorWhite
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Res
import watertracker.composeapp.generated.resources.ic_delete

data class StoryModel(
    val label: String,
    val volume: String,
    val time: String,
    val iconRes: DrawableResource
)

@Composable
fun StoryItem(
    label: String,
    volume: String,
    time: String,
    iconRes: DrawableResource,
    onClickDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(76.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(ColorWhite),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(ColorBlueWhite)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = ColorBlue,
                modifier = Modifier.size(20.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = label,
                fontSize = 16.sp,
                color = ColorBlack,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
            )
            Text(
                text = volume,
                fontSize = 14.sp,
                color = ColorGray,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = time,
            fontSize = 14.sp,
            color = ColorGray,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
        )
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(onClick = onClickDelete) {
            Icon(
                painter = painterResource(Res.drawable.ic_delete),
                contentDescription = "Delete",
                tint = ColorGray,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}
