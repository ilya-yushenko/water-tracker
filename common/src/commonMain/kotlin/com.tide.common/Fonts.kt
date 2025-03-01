package com.tide.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import watertracker.common.generated.resources.Inter_Bold
import watertracker.common.generated.resources.Inter_Medium
import watertracker.common.generated.resources.Inter_Regular
import watertracker.common.generated.resources.Inter_SemiBold
import watertracker.common.generated.resources.Res

object Fonts {
    @Composable
    fun interRegular() = FontFamily(Font(Res.font.Inter_Regular, FontWeight.Normal))

    @Composable
    fun interMedium() = FontFamily(Font(Res.font.Inter_Medium, FontWeight.Medium))

    @Composable
    fun interSemiBold() = FontFamily(Font(Res.font.Inter_SemiBold, FontWeight.SemiBold))

    @Composable
    fun interBold() = FontFamily(Font(Res.font.Inter_Bold, FontWeight.Bold))
}

object AppTypography {
    @Composable
    fun regular() = Fonts.interRegular()

    @Composable
    fun medium() = Fonts.interMedium()

    @Composable
    fun semiBold() = Fonts.interSemiBold()

    @Composable
    fun bold() = Fonts.interBold()
}