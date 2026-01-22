package com.example.androidtemplate.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.compose.ui.graphics.toArgb
import com.example.androidtemplate.TemplateApplication
import com.example.androidtemplate.ui.navigation.AppNavigation
import com.example.androidtemplate.ui.theme.AndroidTemplateTheme

/**
 * MainActivity - Haupt-Activity der Anwendung
 * 
 * Verwendet Jetpack Compose für die UI und hostet die komplette Navigation.
 * Das Theme wird dynamisch basierend auf den Benutzereinstellungen angewendet.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Edge-to-Edge Layout aktivieren
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        setContent {
            MainContent()
        }
    }

    @Composable
    private fun MainContent() {
        val application = application as TemplateApplication
        val isDarkTheme by application.userPreferencesRepository.isDarkTheme.collectAsState(initial = false)
        val accentColor by application.userPreferencesRepository.accentColor.collectAsState(initial = "default")
        val highContrastEnabled by application.userPreferencesRepository.highContrastEnabled.collectAsState(initial = false)
        val largeTextEnabled by application.userPreferencesRepository.largeTextEnabled.collectAsState(initial = false)
        val colorBlindMode by application.userPreferencesRepository.colorBlindMode.collectAsState(initial = "none")
        val statusBarUsesAccentColor by application.userPreferencesRepository.statusBarUsesAccentColor.collectAsState(initial = false)
        
        AndroidTemplateTheme(
            darkTheme = isDarkTheme,
            accentColor = accentColor,
            highContrast = highContrastEnabled,
            largeText = largeTextEnabled,
            colorBlindMode = colorBlindMode
        ) {
            // Statusleiste-Farbe setzen
            val statusBarColor = if (statusBarUsesAccentColor) {
                MaterialTheme.colorScheme.primary.toArgb()
            } else {
                MaterialTheme.colorScheme.surface.toArgb()
            }
            window.statusBarColor = statusBarColor
            
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavigation()
            }
        }
    }
}
