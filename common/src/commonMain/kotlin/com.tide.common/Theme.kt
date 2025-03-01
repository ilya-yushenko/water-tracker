package com.tide.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun appMaterialColors(): Colors {
    val palette = appColorPalette()
    return if (isSystemInDarkTheme()) {
        darkColors(
            primary = palette.primary,
            primaryVariant = palette.primaryVariant,
            secondary = palette.secondary,
            background = palette.background,
            surface = palette.surface,
            onPrimary = palette.onPrimary,
            onBackground = palette.onBackground,
            onSurface = palette.onSurface
        )
    } else {
        lightColors(
            primary = palette.primary,
            primaryVariant = palette.primaryVariant,
            secondary = palette.secondary,
            background = palette.background,
            surface = palette.surface,
            onPrimary = palette.onPrimary,
            onBackground = palette.onBackground,
            onSurface = palette.onSurface
        )
    }
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = appMaterialColors(),
        content = content
    )
}