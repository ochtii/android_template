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
        
        AndroidTemplateTheme(darkTheme = isDarkTheme) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavigation()
            }
        }
    }
}
