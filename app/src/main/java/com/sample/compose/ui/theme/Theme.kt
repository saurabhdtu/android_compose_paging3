package com.sample.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColors(
    primary = BlueGray,
    primaryVariant = BlueGrayDark,
    secondary = Accent,
    background = LightBackground,
    surface = Sand,
    onPrimary = Sand,
    onSecondary = Sand,
    onBackground = BlueGrayDark,
    onSurface = BlueGrayDark,
)

private val DarkColors = darkColors(
    primary = BlueGray,
    primaryVariant = BlueGrayDark,
    secondary = Accent,
    background = DarkBackground,
    surface = SandDark,
    onPrimary = Sand,
    onSecondary = Sand,
    onBackground = Sand,
    onSurface = Sand,
)

@Composable
fun ComposeSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Fixed: Use darkTheme parameter to choose colors
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        typography = AppTypography,
        content = content
    )
}
