package com.tide.settings.ui.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette
import com.tide.settings.ui.DrinkType
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


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
    val colors = appColorPalette()

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(if (isSelected) colors.blue else colors.grayWhite)
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
                tint = if (isSelected) colors.surface else colors.grayDark,
                modifier = Modifier
                    .size(20.dp)
            )
        }
        Text(
            text = model.label,
            fontSize = 14.sp,
            color = if (isSelected) colors.surface else colors.grayDark,
            fontFamily = AppTypography.medium()
        )
        Spacer(modifier = Modifier.width(16.dp))
        Spacer(modifier = Modifier.weight(1f))
    }
}