package org.itsolutions.mydivelog.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = diveLog_theme_light_primary,
    onPrimary = diveLog_theme_light_onPrimary,
    primaryContainer = diveLog_theme_light_primaryContainer,
    onPrimaryContainer = diveLog_theme_light_onPrimaryContainer,
    secondary = diveLog_theme_light_secondary,
    onSecondary = diveLog_theme_light_onSecondary,
    secondaryContainer = diveLog_theme_light_secondaryContainer,
    onSecondaryContainer = diveLog_theme_light_onSecondaryContainer,
    tertiary = diveLog_theme_light_tertiary,
    onTertiary = diveLog_theme_light_onTertiary,
    tertiaryContainer = diveLog_theme_light_tertiaryContainer,
    onTertiaryContainer = diveLog_theme_light_onTertiaryContainer,
    background = diveLog_theme_light_background,
    onBackground = diveLog_theme_light_onBackground,
    surface = diveLog_theme_light_surface,
    onSurface = diveLog_theme_light_onSurface,
    surfaceVariant = diveLog_theme_light_surfaceVariant,
    onSurfaceVariant = diveLog_theme_light_onSurfaceVariant,
    error = diveLog_theme_light_error,
    onError = diveLog_theme_light_onError,
    errorContainer = diveLog_theme_light_errorContainer,
    onErrorContainer = diveLog_theme_light_onErrorContainer,
    outline = diveLog_theme_light_outline,
    outlineVariant = diveLog_theme_light_outlineVariant,
    inverseSurface = diveLog_theme_light_inverseSurface,
    inverseOnSurface = diveLog_theme_light_inverseOnSurface,
    inversePrimary = diveLog_theme_light_inversePrimary,
    surfaceTint = diveLog_theme_light_surfaceTint,
    scrim = diveLog_theme_light_scrim,
    surfaceBright = diveLog_theme_light_surfaceBright,
    surfaceContainer = diveLog_theme_light_surfaceContainer,
    surfaceContainerHigh = diveLog_theme_light_surfaceContainerHigh,
    surfaceContainerHighest = diveLog_theme_light_surfaceContainerHighest,
    surfaceContainerLow = diveLog_theme_light_surfaceContainerLow,
    surfaceContainerLowest = diveLog_theme_light_surfaceContainerLowest,
    surfaceDim = diveLog_theme_light_surfaceDim,
)

private val DarkColors = darkColorScheme(
    primary = diveLog_theme_dark_primary,
    onPrimary = diveLog_theme_dark_onPrimary,
    primaryContainer = diveLog_theme_dark_primaryContainer,
    onPrimaryContainer = diveLog_theme_dark_onPrimaryContainer,
    secondary = diveLog_theme_dark_secondary,
    onSecondary = diveLog_theme_dark_onSecondary,
    secondaryContainer = diveLog_theme_dark_secondaryContainer,
    onSecondaryContainer = diveLog_theme_dark_onSecondaryContainer,
    tertiary = diveLog_theme_dark_tertiary,
    onTertiary = diveLog_theme_dark_onTertiary,
    tertiaryContainer = diveLog_theme_dark_tertiaryContainer,
    onTertiaryContainer = diveLog_theme_dark_onTertiaryContainer,
    background = diveLog_theme_dark_background,
    onBackground = diveLog_theme_dark_onBackground,
    surface = diveLog_theme_dark_surface,
    onSurface = diveLog_theme_dark_onSurface,
    surfaceVariant = diveLog_theme_dark_surfaceVariant,
    onSurfaceVariant = diveLog_theme_dark_onSurfaceVariant,
    error = diveLog_theme_dark_error,
    onError = diveLog_theme_dark_onError,
    errorContainer = diveLog_theme_dark_errorContainer,
    onErrorContainer = diveLog_theme_dark_onErrorContainer,
    outline = diveLog_theme_dark_outline,
    outlineVariant = diveLog_theme_dark_outlineVariant,
    inverseSurface = diveLog_theme_dark_inverseSurface,
    inverseOnSurface = diveLog_theme_dark_inverseOnSurface,
    inversePrimary = diveLog_theme_dark_inversePrimary,
    surfaceTint = diveLog_theme_dark_surfaceTint,
    scrim = diveLog_theme_dark_scrim,
    surfaceBright = diveLog_theme_dark_surfaceBright,
    surfaceContainer = diveLog_theme_dark_surfaceContainer,
    surfaceContainerHigh = diveLog_theme_dark_surfaceContainerHigh,
    surfaceContainerHighest = diveLog_theme_dark_surfaceContainerHighest,
    surfaceContainerLow = diveLog_theme_dark_surfaceContainerLow,
    surfaceContainerLowest = diveLog_theme_dark_surfaceContainerLowest,
    surfaceDim = diveLog_theme_dark_surfaceDim,
)

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