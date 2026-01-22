package com.example.androidtemplate.data.preferences

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
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
}
