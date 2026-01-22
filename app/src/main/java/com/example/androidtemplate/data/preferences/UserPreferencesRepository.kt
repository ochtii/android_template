package com.example.androidtemplate.data.preferences

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository für Benutzereinstellungen
 * 
 * Verwendet DataStore für persistente Einstellungen.
 * Verwaltet Theme-Einstellungen und andere App-Präferenzen.
 */
class UserPreferencesRepository(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

    companion object {
        private val THEME_MODE_KEY = intPreferencesKey("theme_mode")
        private val NOTIFICATIONS_ENABLED_KEY = booleanPreferencesKey("notifications_enabled")
        private val ACCENT_COLOR_KEY = stringPreferencesKey("accent_color")
        private val HIGH_CONTRAST_KEY = booleanPreferencesKey("high_contrast")
        private val LARGE_TEXT_KEY = booleanPreferencesKey("large_text")
        private val COLOR_BLIND_MODE_KEY = stringPreferencesKey("color_blind_mode")
        private val REDUCED_ANIMATIONS_KEY = booleanPreferencesKey("reduced_animations")
    }

    /**
     * Theme Mode als Flow
     * Gibt den aktuellen Theme-Modus zurück (System, Light, Dark)
     */
    val themeMode: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[THEME_MODE_KEY] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    /**
     * Dark Theme Status als Flow
     * Vereinfachte Abfrage ob Dark Theme aktiv ist
     */
    val isDarkTheme: Flow<Boolean> = context.dataStore.data.map { preferences ->
        when (preferences[THEME_MODE_KEY] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) {
            AppCompatDelegate.MODE_NIGHT_YES -> true
            AppCompatDelegate.MODE_NIGHT_NO -> false
            else -> {
                // System default prüfen
                val nightMode = context.resources.configuration.uiMode and 
                    android.content.res.Configuration.UI_MODE_NIGHT_MASK
                nightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES
            }
        }
    }

    /**
     * Benachrichtigungen aktiviert Status
     */
    val notificationsEnabled: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[NOTIFICATIONS_ENABLED_KEY] ?: true
    }

    /**
     * Akzentfarbe
     */
    val accentColor: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[ACCENT_COLOR_KEY] ?: "default"
    }

    /**
     * Hoher Kontrast Modus
     */
    val highContrastEnabled: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[HIGH_CONTRAST_KEY] ?: false
    }

    /**
     * Große Schrift
     */
    val largeTextEnabled: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[LARGE_TEXT_KEY] ?: false
    }

    /**
     * Farbenblind-Modus
     */
    val colorBlindMode: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[COLOR_BLIND_MODE_KEY] ?: "none"
    }

    /**
     * Reduzierte Animationen
     */
    val reducedAnimationsEnabled: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[REDUCED_ANIMATIONS_KEY] ?: false
    }

    /**
     * Theme-Modus setzen
     * 
     * @param mode AppCompatDelegate.MODE_NIGHT_* Konstante
     */
    suspend fun setThemeMode(mode: Int) {
        context.dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = mode
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    /**
     * Benachrichtigungen ein/aus schalten
     * 
     * @param enabled true = aktiviert, false = deaktiviert
     */
    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATIONS_ENABLED_KEY] = enabled
        }
    }

    /**
     * Akzentfarbe setzen
     * 
     * @param color Farbname oder ID
     */
    suspend fun setAccentColor(color: String) {
        context.dataStore.edit { preferences ->
            preferences[ACCENT_COLOR_KEY] = color
        }
    }

    /**
     * Hohen Kontrast ein/aus schalten
     * 
     * @param enabled true = aktiviert, false = deaktiviert
     */
    suspend fun setHighContrastEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[HIGH_CONTRAST_KEY] = enabled
        }
    }

    /**
     * Große Schrift ein/aus schalten
     * 
     * @param enabled true = aktiviert, false = deaktiviert
     */
    suspend fun setLargeTextEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[LARGE_TEXT_KEY] = enabled
        }
    }

    /**
     * Farbenblind-Modus setzen
     * 
     * @param mode Modus-Typ ("none", "protanopia", "deuteranopia", "tritanopia")
     */
    suspend fun setColorBlindMode(mode: String) {
        context.dataStore.edit { preferences ->
            preferences[COLOR_BLIND_MODE_KEY] = mode
        }
    }

    /**
     * Reduzierte Animationen ein/aus schalten
     * 
     * @param enabled true = aktiviert, false = deaktiviert
     */
    suspend fun setReducedAnimationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[REDUCED_ANIMATIONS_KEY] = enabled
        }
    }
}
