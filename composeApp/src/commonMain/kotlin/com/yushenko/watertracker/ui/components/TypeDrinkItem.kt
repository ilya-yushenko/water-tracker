package com.yushenko.watertracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import com.yushenko.watertracker.ui.screens.statistics.DrinkType
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Res


data class TypeDrinkModel(
    val label: String,
    val type: DrinkType,
    val iconRes: DrawableResource? = null
)

@Composable
fun TypeDrinkItem(
    model: TypeDrinkModel,
    isSelected: Boolean = false,
    onClick: (DrinkType) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(if (isSelected) ColorBlue else ColorGrayWhite)
            .height(height = 30.dp)
            .defaultMinSize(minWidth = 80.dp)
            .clickable(onClick = { onClick(model.type) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(16.dp))
        if (model.iconRes != null) {
            Icon(
                painter = painterResource(model.iconRes),
                contentDescription = null,
                tint = if (isSelected) ColorWhite else ColorGrayDark,
                modifier = Modifier
                    .size(20.dp)
            )
        }
        Text(
            text = model.label,
            fontSize = 14.sp,
            color = if (isSelected) ColorWhite else ColorGrayDark,
            fontFamily = FontFamily(Font(Res.font.Inter_Medium))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Spacer(modifier = Modifier.weight(1f))
    }
}