package com.example.cashapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00F15E),
    background = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFEDE7F6),
    tertiary = Color(0xFF228B22),
    surface = Color(0xFFFFFBFE),
    surfaceVariant = Color(0xFFF3E5F5),
    onPrimary = Color.White,
    onSecondary = Color(0xFF3700B3),
    onBackground = Color(0xFFB9FBC0),
    onSurface = Color.Black,
    onSurfaceVariant = Color(0xFF757575),
    error = Color(0xFFB00020),
    errorContainer = Color(0xFFCF6679),
    onError = Color.White
)


val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF00F15E),
    background = Color(0xFF080707),
    secondaryContainer = Color(0xFF003D1C),
    tertiary = Color(0xFF64FFDA),
    surface = Color(0xFF121212),
    surfaceVariant = Color(0xFF1E1E1E),
    onPrimary =  Color.Black,
    onSecondary = Color.White,
    onBackground = Color(0xFFB9FBC0),
    onSurface = Color(0xFFE0E0E0),
    onSurfaceVariant = Color(0xFF757575),
    error = Color(0xFFB00020),
    errorContainer = Color(0xFFCF6679),
    onError = Color.White
)


@Composable
fun CashAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
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
