package com.tide.settings.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import watertracker.feature.settings.generated.resources.Res
import watertracker.feature.settings.generated.resources.ic_settings_arrow

@Composable
fun SettingsChangeItem(
    label: String,
    value: String? = null,
    isArrow: Boolean = true,
    iconRes: DrawableResource,
    onClickDelete: () -> Unit
) {
    val colors = appColorPalette()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colors.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = colors.blue,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = AppTypography.regular(),
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = onClickDelete),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (value != null) {
                Text(
                    text = value,
                    fontSize = 14.sp,
                    color = colors.gray,
                    textAlign = TextAlign.Center,
                    fontFamily = AppTypography.medium(),
                )
            }
            if (isArrow) {
                Icon(
                    painter = painterResource(Res.drawable.ic_settings_arrow),
                    contentDescription = "Change",
                    tint = colors.gray
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}
