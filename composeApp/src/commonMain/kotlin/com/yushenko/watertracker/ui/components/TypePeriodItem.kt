package com.yushenko.watertracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import com.yushenko.watertracker.theme.ColorGrayDark
import com.yushenko.watertracker.theme.ColorGrayWhite
import com.yushenko.watertracker.theme.ColorWhite
import org.jetbrains.compose.resources.Font
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Res


data class TypePeriodModel(
    val period: String,
    val isSelected: Boolean = false
)

@Composable
fun TypePeriodItem(
    model: TypePeriodModel,
    onClick: (TypePeriodModel) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (model.isSelected) ColorBlue else ColorGrayWhite)
            .height(height = 36.dp)
            .clickable(onClick = { onClick(model) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = model.period,
            fontSize = 14.sp,
            color = if (model.isSelected) ColorWhite else ColorGrayDark,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium))
        )
        Spacer(modifier = Modifier.width(20.dp))
    }
}