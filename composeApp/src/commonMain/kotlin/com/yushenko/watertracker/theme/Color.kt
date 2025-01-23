package com.yushenko.watertracker.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Colors {
    val White = Color(0xFFFFFFFF)
    val BrightBlue = Color(0xFF17C6ED)
    val MutedGray = Color(0xFF7E8A8C)
    val DarkTeal = Color(0xFF193238)
    val LightGray = Color(0xFFEBEDED)
}

object LightThemeColors {
    val Primary = Color(0xFFFFFFFF)
    val Background = Color(0xFFF6F8F9)
    val Surface = Color(0xFFFFFFFF)

    val BrightBlue = Color(0xFF17C6ED)
    val DarkTeal = Color(0xFF193238)
    val White = Color(0xFFFFFFFF)
    val SoftGray = Color(0xFFF6F8F9)
    val LightGray = Color(0xFFEBEDED)

}

object DarkThemeColors {
    val Primary = Color(0xFF1E1E1E)
    val Background = Color(0xFF1E1E1E)
    val Surface = Color(0xFF000000)


    val BrightBlue = Color(0xFF0D5E7A)
    val DarkTeal = Color(0xFF0A1F23)

    //    val White = Color(0xFF121212)
    val SoftGray = Color(0xFF1E1E1E)
    val LightGray = Color(0xFFB0B0B0)
    val MutedGray = Color(0xFFD0D0D0)

}

val backgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1E1E1E)
    else Color(0xFFF6F8F9)

val surfaceColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF000000)
    else Color(0xFFFFFFFF)

val textColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFE6E9EA)
    else Color(0xFF193238)



val ColorBackground = Color(0xFFF9FAFB)

val ColorWhite = Color(0xFFFFFFFF)
val ColorWhite80 = Color(0xccffffff)
val ColorWhite10 = Color(0x1affffff)

//val ColorGray = Color(0xFF4B5563)
val ColorBlack = Color(0xff000000)
val ColorGray = Color(0xFF6B7280)
val ColorGrayWhite = Color(0xFFF3F4F6)

val ColorBlue = Color(0xFF1A73FF)
val ColorBlue10 = Color(0x1a1a73ff)

val ColorBlueGradient = Color(0xFF2B7FFF)

val ColorBlueWhite = Color(0xFFEFF6FF)