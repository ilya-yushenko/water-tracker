package com.yushenko.watertracker.ui.components

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorBlack
import com.yushenko.watertracker.theme.ColorBlue
import com.yushenko.watertracker.theme.ColorGray
import com.yushenko.watertracker.theme.ColorWhite
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Inter_SemiBold
import watertracker.composeapp.generated.resources.Res


@Composable
fun StatisticsDetailsItem(
    title: String,
    data: String,
    iconRes: DrawableResource,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(ColorWhite)
            .height(100.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = ColorBlue,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            color = ColorGray,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = data,
            fontSize = 18.sp,
            color = ColorBlack,
            fontFamily = FontFamily(Font(Res.font.Inter_SemiBold)),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}