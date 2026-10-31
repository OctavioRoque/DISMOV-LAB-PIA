package com.example.pia_labdismov.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryVolleyBlue,
    onPrimary = OnPrimaryWhite,
    primaryContainer = PrimaryContainerBlue,
    onPrimaryContainer = OnPrimaryContainerBlue,
    secondary = SecondaryGold,
    onSecondary = OnSecondaryBlack,
    secondaryContainer = SecondaryContainerGold,
    onSecondaryContainer = OnSecondaryContainerGold,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    error = ErrorRed
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryVolleyBlue,
    onPrimary = OnPrimaryWhite,
    primaryContainer = PrimaryContainerBlue,
    onPrimaryContainer = OnPrimaryContainerBlue,
    secondary = SecondaryGold,
    onSecondary = OnSecondaryBlack,
    secondaryContainer = SecondaryContainerGold,
    onSecondaryContainer = OnSecondaryContainerGold,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    error = ErrorRed
)

@Composable
fun VolleyCoachTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}