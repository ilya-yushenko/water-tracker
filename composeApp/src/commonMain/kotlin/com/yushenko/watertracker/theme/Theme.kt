package com.yushenko.watertracker.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


fun lightThemeColors(): Colors {
    return lightColors(
        primary = LightThemeColors.Primary,
        background = LightThemeColors.Background,
        surface = LightThemeColors.Surface,
        onSurface = LightThemeColors.Surface


//        primaryVariant = LightThemeColors.DarkTeal,
//        surface = LightThemeColors.LightGray,
//        onPrimary = LightThemeColors.MutedGray,
//        onSurface = LightThemeColors.BrightBlue
    )
}

fun darkThemeColors(): Colors {
    return darkColors(
        primary = DarkThemeColors.Primary,
        background = DarkThemeColors.Background,
        surface = DarkThemeColors.Surface,
        onSurface = DarkThemeColors.Surface


//        primaryVariant = DarkThemeColors.DarkTeal,
//        surface = DarkThemeColors.BrightBlue,
//        onPrimary = DarkThemeColors.MutedGray,
//        onSurface = DarkThemeColors.White
    )
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkThemeColors()
    } else {
        lightThemeColors()
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}