package com.example.androidtemplate.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Material Design 3 Typography
 * 
 * Definiert die Typografie-Styles für die Anwendung.
 * Kann angepasst werden, um eigene Schriftarten zu verwenden.
 */
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

/**
 * Gibt skalierte Typography zurück basierend auf der großen Schrift-Einstellung
 */
fun getTypography(largeText: Boolean): Typography {
    if (!largeText) return Typography

    val scaleFactor = 1.2f // 20% größer

    return Typography(
        displayLarge = Typography.displayLarge.copy(
            fontSize = Typography.displayLarge.fontSize * scaleFactor,
            lineHeight = Typography.displayLarge.lineHeight * scaleFactor
        ),
        displayMedium = Typography.displayMedium.copy(
            fontSize = Typography.displayMedium.fontSize * scaleFactor,
            lineHeight = Typography.displayMedium.lineHeight * scaleFactor
        ),
        displaySmall = Typography.displaySmall.copy(
            fontSize = Typography.displaySmall.fontSize * scaleFactor,
            lineHeight = Typography.displaySmall.lineHeight * scaleFactor
        ),
        headlineLarge = Typography.headlineLarge.copy(
            fontSize = Typography.headlineLarge.fontSize * scaleFactor,
            lineHeight = Typography.headlineLarge.lineHeight * scaleFactor
        ),
        headlineMedium = Typography.headlineMedium.copy(
            fontSize = Typography.headlineMedium.fontSize * scaleFactor,
            lineHeight = Typography.headlineMedium.lineHeight * scaleFactor
        ),
        headlineSmall = Typography.headlineSmall.copy(
            fontSize = Typography.headlineSmall.fontSize * scaleFactor,
            lineHeight = Typography.headlineSmall.lineHeight * scaleFactor
        ),
        titleLarge = Typography.titleLarge.copy(
            fontSize = Typography.titleLarge.fontSize * scaleFactor,
            lineHeight = Typography.titleLarge.lineHeight * scaleFactor
        ),
        titleMedium = Typography.titleMedium.copy(
            fontSize = Typography.titleMedium.fontSize * scaleFactor,
            lineHeight = Typography.titleMedium.lineHeight * scaleFactor
        ),
        titleSmall = Typography.titleSmall.copy(
            fontSize = Typography.titleSmall.fontSize * scaleFactor,
            lineHeight = Typography.titleSmall.lineHeight * scaleFactor
        ),
        bodyLarge = Typography.bodyLarge.copy(
            fontSize = Typography.bodyLarge.fontSize * scaleFactor,
            lineHeight = Typography.bodyLarge.lineHeight * scaleFactor
        ),
        bodyMedium = Typography.bodyMedium.copy(
            fontSize = Typography.bodyMedium.fontSize * scaleFactor,
            lineHeight = Typography.bodyMedium.lineHeight * scaleFactor
        ),
        bodySmall = Typography.bodySmall.copy(
            fontSize = Typography.bodySmall.fontSize * scaleFactor,
            lineHeight = Typography.bodySmall.lineHeight * scaleFactor
        ),
        labelLarge = Typography.labelLarge.copy(
            fontSize = Typography.labelLarge.fontSize * scaleFactor,
            lineHeight = Typography.labelLarge.lineHeight * scaleFactor
        ),
        labelMedium = Typography.labelMedium.copy(
            fontSize = Typography.labelMedium.fontSize * scaleFactor,
            lineHeight = Typography.labelMedium.lineHeight * scaleFactor
        ),
        labelSmall = Typography.labelSmall.copy(
            fontSize = Typography.labelSmall.fontSize * scaleFactor,
            lineHeight = Typography.labelSmall.lineHeight * scaleFactor
        )
    )
}
