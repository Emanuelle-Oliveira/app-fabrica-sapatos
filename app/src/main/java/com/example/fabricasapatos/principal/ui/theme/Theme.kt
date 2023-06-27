package com.example.fabricasapatos.principal.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
   // primary = Purple200,
   // primaryVariant = Purple700,
   // secondary = Teal200
    primary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_primary,
    onPrimary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onPrimary,
   // primaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_primaryContainer,
   // onPrimaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onPrimaryContainer,
    secondary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_secondary,
    onSecondary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onSecondary,
   // secondaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_secondaryContainer,
    //onSecondaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onSecondaryContainer,
   // tertiary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_tertiary,
   // onTertiary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onTertiary,
   // tertiaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_tertiaryContainer,
   // onTertiaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onTertiaryContainer,
    error = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_error,
  //  errorContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_errorContainer,
    onError = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onError,
  //  onErrorContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onErrorContainer,
    background = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_background,
    onBackground = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onBackground,
    surface = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_surface,
    onSurface = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onSurface,
    /*surfaceVariant = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_surfaceVariant,
    onSurfaceVariant = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_onSurfaceVariant,
    outline = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_outline,
    inverseOnSurface = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_inverseOnSurface,
    inverseSurface = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_inverseSurface,
    inversePrimary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_inversePrimary,
    surfaceTint = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_surfaceTint,
    outlineVariant = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_outlineVariant,
    scrim = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_dark_scrim,*/
)

private val LightColorPalette = lightColors(
    //primary = Purple500,
    //primaryVariant = Purple700,
   // secondary = Teal200
    primary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_primary,
    onPrimary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onPrimary,
    //primaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_primaryContainer,
   // onPrimaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onPrimaryContainer,
    secondary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_secondary,
    onSecondary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onSecondary,
   // secondaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_secondaryContainer,
    //onSecondaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onSecondaryContainer,
   // tertiary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_tertiary,
   // onTertiary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onTertiary,
  //  tertiaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_tertiaryContainer,
  //  onTertiaryContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onTertiaryContainer,
    error = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_error,
  //  errorContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_errorContainer,
    onError = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onError,
  //  onErrorContainer = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onErrorContainer,
    background = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_background,
    onBackground = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onBackground,
    surface = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_surface,
    onSurface = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onSurface,
  //  surfaceVariant = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_surfaceVariant,
   // onSurfaceVariant = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_onSurfaceVariant,
  //  outline = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_outline,
  //  inverseOnSurface = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_inverseOnSurface,
  //  inverseSurface = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_inverseSurface,
 //   inversePrimary = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_inversePrimary,
  //  surfaceTint = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_surfaceTint,
  //  outlineVariant = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_outlineVariant,
   // scrim = com.example.fabricasapatos.ui.activities.client.ui.theme.md_theme_light_scrim,
)

@Composable
fun FabricaSapatosTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}