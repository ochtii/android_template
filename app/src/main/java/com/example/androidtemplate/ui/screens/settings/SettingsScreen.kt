package com.example.androidtemplate.ui.screens.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.androidtemplate.TemplateApplication
import kotlinx.coroutines.launch

/**
 * Einstellungen Screen
 * 
 * Zeigt verschiedene App-Einstellungen an:
 * - Theme (Dark/Light/System)
 * - Benachrichtigungen
 * - Weitere Einstellungen
 */
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val application = context.applicationContext as TemplateApplication
    val scope = rememberCoroutineScope()
    
    val currentThemeMode by application.userPreferencesRepository.themeMode
        .collectAsState(initial = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    val notificationsEnabled by application.userPreferencesRepository.notificationsEnabled
        .collectAsState(initial = true)
    
    var showThemeDialog by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Darstellung Sektion
        Text(
            text = "Darstellung",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            SettingItem(
                icon = when (currentThemeMode) {
                    AppCompatDelegate.MODE_NIGHT_YES -> Icons.Default.DarkMode
                    AppCompatDelegate.MODE_NIGHT_NO -> Icons.Default.LightMode
                    else -> Icons.Default.PhoneAndroid
                },
                title = "Theme",
                subtitle = when (currentThemeMode) {
                    AppCompatDelegate.MODE_NIGHT_YES -> "Dunkel"
                    AppCompatDelegate.MODE_NIGHT_NO -> "Hell"
                    else -> "System"
                },
                onClick = { showThemeDialog = true }
            )
        }

        // Allgemein Sektion
        Text(
            text = "Allgemein",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            SettingItemWithSwitch(
                icon = Icons.Default.Notifications,
                title = "Benachrichtigungen",
                subtitle = "App-Benachrichtigungen aktivieren",
                checked = notificationsEnabled,
                onCheckedChange = { enabled ->
                    scope.launch {
                        application.userPreferencesRepository.setNotificationsEnabled(enabled)
                    }
                }
            )
        }
    }
    
    // Theme Dialog
    if (showThemeDialog) {
        ThemeSelectionDialog(
            currentThemeMode = currentThemeMode,
            onDismiss = { showThemeDialog = false },
            onThemeSelected = { mode ->
                scope.launch {
                    application.userPreferencesRepository.setThemeMode(mode)
                }
                showThemeDialog = false
            }
        )
    }
}

/**
 * Einstellungs-Item mit Icon und Click-Handler
 */
@Composable
fun SettingItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Einstellungs-Item mit Switch
 */
@Composable
fun SettingItemWithSwitch(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

/**
 * Theme-Auswahl Dialog
 */
@Composable
fun ThemeSelectionDialog(
    currentThemeMode: Int,
    onDismiss: () -> Unit,
    onThemeSelected: (Int) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Theme wählen") },
        text = {
            Column {
                ThemeOption(
                    title = "System",
                    isSelected = currentThemeMode == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
                    onClick = { onThemeSelected(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) }
                )
                ThemeOption(
                    title = "Hell",
                    isSelected = currentThemeMode == AppCompatDelegate.MODE_NIGHT_NO,
                    onClick = { onThemeSelected(AppCompatDelegate.MODE_NIGHT_NO) }
                )
                ThemeOption(
                    title = "Dunkel",
                    isSelected = currentThemeMode == AppCompatDelegate.MODE_NIGHT_YES,
                    onClick = { onThemeSelected(AppCompatDelegate.MODE_NIGHT_YES) }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Abbrechen")
            }
        }
    )
}

/**
 * Theme-Option im Dialog
 */
@Composable
fun ThemeOption(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title)
    }
}
