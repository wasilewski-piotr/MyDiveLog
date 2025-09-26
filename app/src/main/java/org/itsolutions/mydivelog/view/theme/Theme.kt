package org.itsolutions.mydivelog.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = darkGrey,
    onPrimary = white,
    secondary = darkGrey,
    secondaryContainer = darkGrey,
    onSecondaryContainer = white,
    background = white,
    onBackground = darkGrey,
    surface = white,
    onSurface = darkGrey,
    onSurfaceVariant = darkGrey,
    outlineVariant = darkGrey,
    surfaceContainer = white,
    surfaceContainerHigh = primary,
    surfaceContainerHighest = Color(0xFFE5E4E2),
    surfaceContainerLow = secondary,
    surfaceContainerLowest = tertiary,
    outline = darkGrey,




    primaryContainer = Color.Green,
    onPrimaryContainer = Color.Green,
    inversePrimary = Color.Green,
    onSecondary = Color.Green,
    tertiary = Color.Green,
    onTertiary = Color.Green,
    tertiaryContainer = Color.Green,
    onTertiaryContainer = Color.Green,
    surfaceVariant = Color.Green,
    surfaceTint = Color.Green,
    inverseSurface = Color.Green,
    inverseOnSurface = Color.Green,
    scrim = Color.Green,
    surfaceBright = Color.Green,
    surfaceDim = Color.Green,
    primaryFixed = Color.Green,
    primaryFixedDim = Color.Green,
    onPrimaryFixed = Color.Green,
    onPrimaryFixedVariant = Color.Green,
    secondaryFixed = Color.Green,
    secondaryFixedDim = Color.Green,
    onSecondaryFixed = Color.Green,
    onSecondaryFixedVariant = Color.Green,
    tertiaryFixed = Color.Green,
    tertiaryFixedDim = Color.Green,
    onTertiaryFixed = Color.Green,
    onTertiaryFixedVariant = Color.Green,
)

private val DarkColors = darkColorScheme()

@Composable
fun MyDiveLogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = DiveLogTypography,
        content = content
    )
}

@Composable
fun DialogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColorScheme() else lightColorScheme(
        primary = darkGrey,
        surfaceContainerHigh = white,
    )

    MaterialTheme(
        colorScheme = colors,
        typography = DiveLogTypography,
        content = content
    )
}

@Composable
fun DialogThemeInverted(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColorScheme() else lightColorScheme(
        primary = white,
        onPrimary = darkGrey
    )

    MaterialTheme(
        colorScheme = colors,
        typography = DiveLogTypography,
        content = content
    )
}