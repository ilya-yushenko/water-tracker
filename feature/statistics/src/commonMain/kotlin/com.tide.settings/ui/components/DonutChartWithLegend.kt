package com.tide.settings.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette
import org.jetbrains.compose.resources.stringResource
import watertracker.feature.statistics.generated.resources.Res
import watertracker.feature.statistics.generated.resources.statistics_screen_distribution


data class ChartData(val label: String, val percentage: Float, val color: Color)

@Composable
fun DonutChartWithLegend(data: List<ChartData>) {
    val colors = appColorPalette()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colors.surface)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(Res.string.statistics_screen_distribution),
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            fontFamily = AppTypography.semiBold(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Canvas(
                    modifier = Modifier
                        .size(160.dp)
                        .padding(16.dp)
                        .align(Alignment.Center)
                ) {
                    var startAngle = -90f
                    val totalPercentage = 360f
                    val gapAngle = 2f

                    data.forEach { chartData ->
                        val sweepAngle = totalPercentage * chartData.percentage / 100f - gapAngle
                        drawArc(
                            color = chartData.color,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            style = Stroke(width = 50f)
                        )
                        startAngle += sweepAngle + gapAngle
                    }
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    data.forEach { chartData ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(chartData.color)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = chartData.label,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Start,
                                fontFamily = AppTypography.medium(),
                                color = colors.grayDark
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "${chartData.percentage.toInt()} %",
                                fontSize = 14.sp,
                                textAlign = TextAlign.Start,
                                fontFamily = AppTypography.medium()
                            )

                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}