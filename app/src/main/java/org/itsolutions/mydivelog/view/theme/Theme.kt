package org.itsolutions.mydivelog.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    background = diveLog_theme_light_background,
    onPrimary = diveLog_theme_light_onPrimary
)

private val DarkColors = darkColorScheme(
    background = diveLog_theme_dark_background,
    onPrimary = diveLog_theme_dark_onPrimary
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