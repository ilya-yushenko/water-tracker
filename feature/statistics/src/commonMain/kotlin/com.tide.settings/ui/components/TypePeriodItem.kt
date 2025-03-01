package com.tide.settings.ui.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette
import com.tide.settings.ui.PeriodType

@Composable
fun TypePeriodItem(
    label: String,
    type: PeriodType,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: (PeriodType) -> Unit,
) {
    val colors = appColorPalette()

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) colors.blue else colors.surface)
            .height(height = 38.dp)
            .clickable(onClick = { onClick(type) }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = if (isSelected) colors.surface else colors.grayDark,
            fontFamily = AppTypography.medium()
        )
    }
}