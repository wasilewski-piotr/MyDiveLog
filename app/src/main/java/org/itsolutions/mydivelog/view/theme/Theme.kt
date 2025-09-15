package org.itsolutions.mydivelog.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightGrayBlueColorScheme = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color(0xFF1A237E),
    primaryContainer = Color(0xFFE3F2FD),
    onPrimaryContainer = Color(0xFF0D47A1),
    inversePrimary = Color(0xFF90CAF9),

    secondary = Color(0xFFE3F2FD),
    onSecondary = Color(0xFF212121),
    secondaryContainer = Color(0xFFBBDEFB),
    onSecondaryContainer = Color(0xFF0D47A1),

    tertiary = Color(0xFFD7CCC8),
    onTertiary = Color(0xFF3E2723),
    tertiaryContainer = Color(0xFFEFEBE9),
    onTertiaryContainer = Color(0xFF4E342E),

    background = Color(0xFFF5F5F5),
    onBackground = Color(0xFF212121),

    surface = Color(0xFFF5F5F5),
    onSurface = Color(0xFF212121),
    surfaceVariant = Color(0xFFE3F2FD),
    onSurfaceVariant = Color(0xFF212121),

    surfaceTint = Color(0xFFD0E8FF),
    inverseSurface = Color(0xFF212121),
    inverseOnSurface = Color(0xFFF5F5F5),

    error = Color(0xFFB00020),
    onError = Color.White,
    errorContainer = Color(0xFFFCD8DF),
    onErrorContainer = Color(0xFF370617),

    outline = Color(0xFFCCCCCC),
    outlineVariant = Color(0xFFE0E0E0),

    scrim = Color(0x80000000),

    surfaceBright = Color(0xFFFFFFFF),
    surfaceContainer = Color(0xFFF2F2F2),
    surfaceContainerHigh = Color(0xFFECECEC),
    surfaceContainerHighest = Color(0xFFE5E5E5),
    surfaceContainerLow = Color(0xFFF8F8F8),
    surfaceContainerLowest = Color(0xFFFAFAFA),
    surfaceDim = Color(0xFFE0E0E0)
)

val DarkGrayBlueColorScheme = darkColorScheme(
    primary = Color(0xFF64B5F6), // Light blue 400
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF0D47A1),
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF9E9E9E),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF616161),
    onSecondaryContainer = Color.White,
    background = Color(0xFF121212),
    onBackground = Color(0xFFE0E0E0),
    surface = Color(0xFF121212),
    onSurface = Color(0xFFE0E0E0),
    error = Color(0xFFCF6679),
    onError = Color.Black,
    outline = Color(0xFF757575),
)


@Composable
fun MyDiveLogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkGrayBlueColorScheme else LightGrayBlueColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = DiveLogTypography,
        content = content
    )
}