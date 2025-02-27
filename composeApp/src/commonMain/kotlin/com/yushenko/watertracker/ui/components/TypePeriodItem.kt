package com.yushenko.watertracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorBlue
import com.yushenko.watertracker.theme.ColorGrayDark
import com.yushenko.watertracker.theme.ColorWhite
import com.yushenko.watertracker.ui.screens.statistics.PeriodType
import org.jetbrains.compose.resources.Font
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Res

@Composable
fun TypePeriodItem(
    label: String,
    type: PeriodType,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: (PeriodType) -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) ColorBlue else ColorWhite)
            .height(height = 38.dp)
            .clickable(onClick = { onClick(type) }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = if (isSelected) ColorWhite else ColorGrayDark,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium))
        )
    }
}