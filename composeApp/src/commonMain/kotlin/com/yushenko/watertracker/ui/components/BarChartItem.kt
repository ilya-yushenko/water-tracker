package com.yushenko.watertracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorBlue
import com.yushenko.watertracker.theme.ColorGray
import com.yushenko.watertracker.theme.ColorGrayDark
import org.jetbrains.compose.resources.Font
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Inter_Regular
import watertracker.composeapp.generated.resources.Res


data class DiagramModel(
    val value: Int,
    val header: String
)

@Composable
fun BarChartItem(
    model: DiagramModel,
    widthColumn: Dp = 45.dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(200.dp)
            .width(widthColumn)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "${model.value} ml",
            fontSize = 8.sp,
            color = ColorGray,
            fontFamily = FontFamily(Font(Res.font.Inter_Regular))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((model.value / 12).dp)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(ColorBlue)
        )
        Text(
            text = model.header,
            fontSize = 10.sp,
            color = ColorGrayDark,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium))
        )
    }
}