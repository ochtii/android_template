package com.example.androidtemplate.ui.screens.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Favorite
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
 * - Akzentfarbe
 * - Barrierefreiheit (Hoher Kontrast, Große Schrift, Farbenblind-Modus, Reduzierte Animationen)
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
    val accentColor by application.userPreferencesRepository.accentColor
        .collectAsState(initial = "default")
    val highContrastEnabled by application.userPreferencesRepository.highContrastEnabled
        .collectAsState(initial = false)
    val largeTextEnabled by application.userPreferencesRepository.largeTextEnabled
        .collectAsState(initial = false)
    val colorBlindMode by application.userPreferencesRepository.colorBlindMode
        .collectAsState(initial = "none")
    val reducedAnimationsEnabled by application.userPreferencesRepository.reducedAnimationsEnabled
        .collectAsState(initial = false)
    
    var showThemeDialog by remember { mutableStateOf(false) }
    var showAccentColorDialog by remember { mutableStateOf(false) }
    var showColorBlindDialog by remember { mutableStateOf(false) }
    
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
                    AppCompatDelegate.MODE_NIGHT_YES -> Icons.Default.Settings
                    AppCompatDelegate.MODE_NIGHT_NO -> Icons.Default.Info
                    else -> Icons.Default.Notifications
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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            SettingItem(
                icon = Icons.Default.Star,
                title = "Akzentfarbe",
                subtitle = when (accentColor) {
                    "default" -> "Standard"
                    "blue" -> "Blau"
                    "green" -> "Grün"
                    "purple" -> "Lila"
                    "orange" -> "Orange"
                    "pink" -> "Rosa"
                    else -> "Benutzerdefiniert"
                },
                onClick = { showAccentColorDialog = true }
            )
        }

        // Barrierefreiheit Sektion
        Text(
            text = "Barrierefreiheit",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column {
                SettingItemWithSwitch(
                    icon = Icons.Default.Person,
                    title = "Hoher Kontrast",
                    subtitle = "Erhöht den Kontrast für bessere Lesbarkeit",
                    checked = highContrastEnabled,
                    onCheckedChange = { enabled ->
                        scope.launch {
                            application.userPreferencesRepository.setHighContrastEnabled(enabled)
                        }
                    }
                )

                Divider()

                SettingItemWithSwitch(
                    icon = Icons.Default.Info,
                    title = "Große Schrift",
                    subtitle = "Vergrößert die Schriftgröße in der App",
                    checked = largeTextEnabled,
                    onCheckedChange = { enabled ->
                        scope.launch {
                            application.userPreferencesRepository.setLargeTextEnabled(enabled)
                        }
                    }
                )

                Divider()

                SettingItem(
                    icon = Icons.Default.Star,
                    title = "Farbenblind-Modus",
                    subtitle = when (colorBlindMode) {
                        "none" -> "Deaktiviert"
                        "protanopia" -> "Protanopie (Rot-Grün)"
                        "deuteranopia" -> "Deuteranopie (Rot-Grün)"
                        "tritanopia" -> "Tritanopie (Blau-Gelb)"
                        else -> "Unbekannt"
                    },
                    onClick = { showColorBlindDialog = true }
                )

                Divider()

                SettingItemWithSwitch(
                    icon = Icons.Default.Favorite,
                    title = "Reduzierte Animationen",
                    subtitle = "Deaktiviert oder reduziert Animationen",
                    checked = reducedAnimationsEnabled,
                    onCheckedChange = { enabled ->
                        scope.launch {
                            application.userPreferencesRepository.setReducedAnimationsEnabled(enabled)
                        }
                    }
                )
            }
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

    // Akzentfarbe Dialog
    if (showAccentColorDialog) {
        AccentColorSelectionDialog(
            currentAccentColor = accentColor,
            onDismiss = { showAccentColorDialog = false },
            onColorSelected = { color ->
                scope.launch {
                    application.userPreferencesRepository.setAccentColor(color)
                }
                showAccentColorDialog = false
            }
        )
    }

    // Farbenblind-Modus Dialog
    if (showColorBlindDialog) {
        ColorBlindModeDialog(
            currentMode = colorBlindMode,
            onDismiss = { showColorBlindDialog = false },
            onModeSelected = { mode ->
                scope.launch {
                    application.userPreferencesRepository.setColorBlindMode(mode)
                }
                showColorBlindDialog = false
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
 * Akzentfarbe-Auswahl Dialog
 */
@Composable
fun AccentColorSelectionDialog(
    currentAccentColor: String,
    onDismiss: () -> Unit,
    onColorSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Akzentfarbe wählen") },
        text = {
            Column {
                AccentColorOption(
                    title = "Standard",
                    isSelected = currentAccentColor == "default",
                    onClick = { onColorSelected("default") }
                )
                AccentColorOption(
                    title = "Blau",
                    isSelected = currentAccentColor == "blue",
                    onClick = { onColorSelected("blue") }
                )
                AccentColorOption(
                    title = "Grün",
                    isSelected = currentAccentColor == "green",
                    onClick = { onColorSelected("green") }
                )
                AccentColorOption(
                    title = "Lila",
                    isSelected = currentAccentColor == "purple",
                    onClick = { onColorSelected("purple") }
                )
                AccentColorOption(
                    title = "Orange",
                    isSelected = currentAccentColor == "orange",
                    onClick = { onColorSelected("orange") }
                )
                AccentColorOption(
                    title = "Rosa",
                    isSelected = currentAccentColor == "pink",
                    onClick = { onColorSelected("pink") }
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
 * Farbenblind-Modus Dialog
 */
@Composable
fun ColorBlindModeDialog(
    currentMode: String,
    onDismiss: () -> Unit,
    onModeSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Farbenblind-Modus") },
        text = {
            Column {
                ColorBlindOption(
                    title = "Deaktiviert",
                    isSelected = currentMode == "none",
                    onClick = { onModeSelected("none") }
                )
                ColorBlindOption(
                    title = "Protanopie (Rot-Grün)",
                    isSelected = currentMode == "protanopia",
                    onClick = { onModeSelected("protanopia") }
                )
                ColorBlindOption(
                    title = "Deuteranopie (Rot-Grün)",
                    isSelected = currentMode == "deuteranopia",
                    onClick = { onModeSelected("deuteranopia") }
                )
                ColorBlindOption(
                    title = "Tritanopie (Blau-Gelb)",
                    isSelected = currentMode == "tritanopia",
                    onClick = { onModeSelected("tritanopia") }
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
 * Akzentfarbe-Option im Dialog
 */
@Composable
fun AccentColorOption(
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

/**
 * Farbenblind-Option im Dialog
 */
@Composable
fun ColorBlindOption(
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
