package com.example.androidtemplate

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.androidtemplate.data.preferences.UserPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * Application-Klasse für das Android Template.
 * 
 * Diese Klasse wird beim Start der App initialisiert und ist verantwortlich für:
 * - Initialisierung von App-weiten Komponenten
 * - Theme-Einstellungen anwenden
 * - Dependency Injection (bei Bedarf)
 */
class TemplateApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    
    lateinit var userPreferencesRepository: UserPreferencesRepository
        private set

    override fun onCreate() {
        super.onCreate()
        
        // Repository initialisieren
        userPreferencesRepository = UserPreferencesRepository(this)
        
        // Theme aus Einstellungen laden und anwenden
        applicationScope.launch {
            val themeMode = userPreferencesRepository.themeMode.first()
            AppCompatDelegate.setDefaultNightMode(themeMode)
        }
    }
}
