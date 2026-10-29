package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val RetroDarkColorScheme = darkColorScheme(
    primary = RetroYellow,
    onPrimary = RetroBlack,
    secondary = RetroBlue,
    onSecondary = RetroBlack,
    tertiary = RetroGreen,
    background = BgDark,
    onBackground = TextInk,
    surface = PanelDark,
    onSurface = TextInk,
    outline = LineBorder
)

@Composable
fun PixelBoxTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = RetroDarkColorScheme,
        typography = PixelBoxTypography,
        content = content
    )
}
