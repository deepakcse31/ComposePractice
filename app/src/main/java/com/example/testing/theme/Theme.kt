package com.example.testing.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val SuccessLightColor = Color(0xFF436915)
val SuccessLightContainerColor = Color(0xFFC2F18D)

val SuccessDarkColor = Color(0xFFA7D474)
val SuccessDarkContainerColor = Color(0xFF2D5000)

private val DarkColorPalette = darkColors(
    background = background,
    onBackground = background800,
    primary = purple200,
    primaryVariant = purple500,
    secondary = purple500,
    onPrimary = Color.White,
    onSecondary = Color.White


)

private val LightColorPalette = lightColors(
    background = Color.White,
    onBackground = Color.White,
    surface = Color.White,
    primary = purple200,
    primaryVariant = purple500,
    secondary = purple500,
    onPrimary = Color.White,
    onSecondary = Color.White
)

@Composable
fun DisneyComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val typography = if (darkTheme) {
        DarkTypography
    } else {
        LightTypography
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}