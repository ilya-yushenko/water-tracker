package com.tide.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class AppColorPalette(
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val onPrimary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val bottomNav: Color,

    val blue: Color,
    val blue10: Color,
    val blueGradient: Color,
    val blueWhite: Color,

    val black: Color,
    val white: Color,

    val gray: Color,
    val gray20: Color,
    val gray30: Color,
    val lightGray: Color,
    val grayDark: Color,
    val grayWhite: Color,

    val headerText: Color,
    val headerText80: Color,
    val headerText10: Color
)

private val LightColorPalette = AppColorPalette(
    primary = Color(0xFF1A73FF),
    primaryVariant = Color(0xFF1665D0),
    secondary = Color(0xFFEFF6FF),
    background = Color(0xFFF9FAFB),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF000000),
    bottomNav = Color(0xffffffff),

    black = Color(0xFF000000),
    white = Color(0xFFFFFFFF),

    blue = Color(0xFF1A73FF),
    blue10 = Color(0x1A1A73FF),
    blueGradient = Color(0xFF2B7FFF),
    blueWhite = Color(0xFFEFF6FF),

    gray = Color(0xFF6B7280),
    gray20 = Color(0x336b7280),
    gray30 = Color(0x4d6b7280),
    lightGray = Color(0xFFEBEDED),
    grayDark = Color(0xFF4B5563),
    grayWhite = Color(0xFFE5E7EB),

    headerText = Color(0xFFFFFFFF),
    headerText80 = Color(0xCCFFFFFF),
    headerText10 = Color(0x1AFFFFFF),
)

private val DarkColorPalette = AppColorPalette(
    primary = Color(0xFF1A73FF),
    primaryVariant = Color(0xFF1665D0),
    secondary = Color(0xFF001F3F),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color(0xFFFFFFFF),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
    bottomNav = Color(0xff000000),

    black = Color(0xFF000000),
    white = Color(0xFFFFFFFF),

    blue = Color(0xFF1A73FF),
    blue10 = Color(0x1A1A73FF),
    blueGradient = Color(0xFF2B7FFF),
    blueWhite = Color(0xFFEFF6FF),

    gray = Color(0xFF6B7280),
    gray20 = Color(0x336b7280),
    gray30 = Color(0x4d6b7280),
    lightGray = Color(0xFFEBEDED),
    grayDark = Color(0xFF4B5563),
    grayWhite = Color(0xFFE5E7EB),

    headerText = Color(0xFF121212),
    headerText80 = Color(0xcc121212),
    headerText10 = Color(0x1a121212),
)

@Composable
fun appColorPalette(): AppColorPalette =
    if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette