package com.example.androidtemplate.ui.screens.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Über Screen
 * 
 * Zeigt Informationen über die App:
 * - Version
 * - Entwickler
 * - Beschreibung
 * - Lizenz
 */
@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Über die App",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Version
        AboutInfoCard(
            title = "Version",
            content = "1.0.0"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Entwickler
        AboutInfoCard(
            title = "Entwickler",
            content = "Ihr Name"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Beschreibung
        AboutInfoCard(
            title = "Beschreibung",
            content = "Ein modulares Template für Android-Apps mit Navigation Drawer, Bottom Navigation und Theme-Support. Perfekt als Basis für neue Projekte."
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Lizenz
        AboutInfoCard(
            title = "Lizenz",
            content = "MIT License"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Features
        Text(
            text = "Features",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        FeatureCard("✓ Material Design 3")
        FeatureCard("✓ Jetpack Compose UI")
        FeatureCard("✓ Navigation Drawer & Bottom Navigation")
        FeatureCard("✓ Dark/Light Theme Support")
        FeatureCard("✓ DataStore Preferences")
        FeatureCard("✓ Modulare Architektur")
        FeatureCard("✓ Kotlin & Coroutines")
    }
}

/**
 * Info Card für About-Informationen
 */
@Composable
fun AboutInfoCard(
    title: String,
    content: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Feature Card
 */
@Composable
fun FeatureCard(feature: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = feature,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(12.dp)
        )
    }
}
