package com.yushenko.watertracker.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorWhite
import com.yushenko.watertracker.theme.ColorWhite10
import com.yushenko.watertracker.theme.ColorWhite80
import org.jetbrains.compose.resources.Font
import watertracker.composeapp.generated.resources.Inter_Bold
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Res


@Composable
fun WaterCircularIndicator(
    currentWater: Int,
    targetWater: Int,
    modifier: Modifier = Modifier
) {
    val waterColor = ColorWhite80
    val shadowWaterColor = ColorWhite10
    val progress = currentWater.toFloat() / targetWater.toFloat()
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.aspectRatio(1f)
    ) {
        CircularProgressIndicator(
            progress = 100f,
            modifier = Modifier.fillMaxSize(),
            strokeWidth = 20.dp,
            color = shadowWaterColor
        )
        CircularProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier.fillMaxSize(),
            strokeWidth = 20.dp,
            color = waterColor,
            strokeCap = StrokeCap.Round
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${(progress * 100).toInt()}%",
                fontSize = 30.sp,
                color = ColorWhite,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(Res.font.Inter_Bold)),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "$currentWater/$targetWater ml",
                fontSize = 14.sp,
                color = ColorWhite80,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
            )
        }
    }
}