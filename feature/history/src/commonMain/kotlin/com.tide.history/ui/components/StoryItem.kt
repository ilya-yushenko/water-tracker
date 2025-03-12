package com.tide.history.ui.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette
import com.tide.history.model.HistoryDrinkIntakeRecord
import com.tide.history.model.toIconRes
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import watertracker.feature.history.generated.resources.Res
import watertracker.feature.history.generated.resources.ic_delete

data class StoryModel(
    val recordId: String,
    val label: String,
    val volume: Int,
    val time: Long,
    val iconRes: DrawableResource
)

@Composable
fun StoryItem(
    label: String,
    volume: Int,
    time: String,
    iconRes: DrawableResource,
    onClickDelete: () -> Unit
) {
    val colors = appColorPalette()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(76.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colors.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(colors.secondary)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = colors.blue,
                modifier = Modifier.size(20.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = label,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = AppTypography.medium(),
            )
            Text(
                text = "$volume ml",
                fontSize = 14.sp,
                color = colors.gray,
                textAlign = TextAlign.Center,
                fontFamily = AppTypography.medium(),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = time,
            fontSize = 14.sp,
            color = colors.gray,
            textAlign = TextAlign.Center,
            fontFamily = AppTypography.medium(),
        )
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(onClick = onClickDelete) {
            Icon(
                painter = painterResource(Res.drawable.ic_delete),
                contentDescription = "Delete",
                tint = colors.gray,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}

fun HistoryDrinkIntakeRecord.toStoryModel() =
    StoryModel(
        recordId = id,
        label = name,
        volume = amount,
        time = createdAt,
        iconRes = drinkType.toIconRes()
    )