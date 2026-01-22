# Android Template

Ein modernes Android-App-Template mit Material Design 3, Jetpack Compose, Navigation Drawer und Bottom Navigation. Perfekt als Basis für neue Android-Projekte.

## 🚀 Features

- ✅ **Material Design 3** - Moderne UI nach aktuellen Design-Guidelines
- ✅ **Jetpack Compose** - Deklaratives UI-Framework für Android
- ✅ **Navigation Drawer** - Seitliches Navigationsmenü
- ✅ **Bottom Navigation** - Untere Navigationsleiste
- ✅ **Dark/Light Theme** - Automatischer Theme-Wechsel mit Benutzereinstellung
- ✅ **DataStore** - Moderne Preferences-Verwaltung
- ✅ **Modulare Architektur** - Klar strukturiert und erweiterbar
- ✅ **Kotlin & Coroutines** - Moderne Android-Entwicklung

## 📱 Screenshots

Die App enthält folgende Demo-Screens:
- **Startseite** - Willkommens-Screen mit Feature-Übersicht
- **Dashboard** - Statistik-Karten Template
- **Profil** - Benutzerprofil-Template
- **Einstellungen** - Theme-Auswahl und App-Einstellungen
- **Über** - App-Informationen

## 🏗️ Projektstruktur

```
app/
├── src/main/
│   ├── java/com/example/androidtemplate/
│   │   ├── data/
│   │   │   └── preferences/
│   │   │       └── UserPreferencesRepository.kt    # Einstellungen-Verwaltung
│   │   ├── ui/
│   │   │   ├── navigation/
│   │   │   │   ├── AppNavigation.kt                # Haupt-Navigation
│   │   │   │   ├── NavigationComponents.kt         # Nav-UI-Komponenten
│   │   │   │   └── NavigationDestination.kt        # Navigation-Routen
│   │   │   ├── screens/
│   │   │   │   ├── about/
│   │   │   │   │   └── AboutScreen.kt              # Über-Screen
│   │   │   │   ├── dashboard/
│   │   │   │   │   └── DashboardScreen.kt          # Dashboard-Screen
│   │   │   │   ├── home/
│   │   │   │   │   └── HomeScreen.kt               # Home-Screen
│   │   │   │   ├── profile/
│   │   │   │   │   └── ProfileScreen.kt            # Profil-Screen
│   │   │   │   └── settings/
│   │   │   │       └── SettingsScreen.kt           # Einstellungen-Screen
│   │   │   ├── theme/
│   │   │   │   ├── Theme.kt                        # Theme-Definition
│   │   │   │   └── Type.kt                         # Typography
│   │   │   └── MainActivity.kt                     # Haupt-Activity
│   │   └── TemplateApplication.kt                  # Application-Klasse
│   └── res/
│       ├── values/
│       │   ├── colors.xml                          # Farbdefinitionen
│       │   ├── strings.xml                         # String-Ressourcen
│       │   └── themes.xml                          # Theme-Styles
│       └── xml/
│           ├── backup_rules.xml
│           └── data_extraction_rules.xml
└── build.gradle.kts
```

## 🛠️ Technologien

- **Sprache:** Kotlin
- **UI-Framework:** Jetpack Compose
- **Navigation:** Navigation Component
- **Architektur:** MVVM-ähnlich
- **Async:** Kotlin Coroutines & Flow
- **Preferences:** DataStore
- **Material Design:** Material 3

## 📦 Dependencies

```kotlin
// Core
androidx.core:core-ktx:1.12.0
androidx.appcompat:appcompat:1.6.1
com.google.android.material:material:1.11.0

// Jetpack Compose
androidx.compose:compose-bom:2023.10.01
androidx.compose.ui:ui
androidx.compose.material3:material3
androidx.activity:activity-compose:1.8.2

// Navigation
androidx.navigation:navigation-compose:2.7.6

// DataStore
androidx.datastore:datastore-preferences:1.0.0

// Coroutines
org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3
```

## 🚀 Erste Schritte

### Voraussetzungen

- **Android Studio**: Hedgehog (2023.1.1) oder neuer
- **JDK**: 21 (Temurin/OpenJDK)
- **Android SDK**: API Level 34
- **Minimum SDK**: API Level 24 (Android 7.0)

### Android SDK Setup

**Für lokale Entwicklung:**
1. Android Studio öffnen
2. SDK Manager öffnen (Tools → SDK Manager)
3. Android SDK Build-Tools 34.0.0 installieren
4. Android SDK Platform 34 installieren
5. Android SDK Command-line Tools installieren

**SDK Pfad konfigurieren:**
Bearbeiten Sie `local.properties`:
```properties
sdk.dir=/Users/IhrName/Library/Android/sdk  # macOS
# oder
sdk.dir=C:\\Users\\IhrName\\AppData\\Local\\Android\\Sdk  # Windows
# oder
sdk.dir=/home/IhrName/Android/Sdk  # Linux
```

### Installation

1. Repository klonen:
```bash
git clone https://github.com/ochtii/android_template.git
cd android_template
```

2. Projekt in Android Studio öffnen

3. Gradle Sync durchführen (Android Studio macht das automatisch)

4. App auf Emulator oder Gerät ausführen

### Anpassung

1. **Package Name ändern:**
   - Refactor → Rename Package
   - In `build.gradle.kts` anpassen: `namespace` und `applicationId`

2. **App Name ändern:**
   - In `res/values/strings.xml` → `app_name`

3. **Farben anpassen:**
   - In `res/values/colors.xml` oder `ui/theme/Theme.kt`

4. **Neue Screens hinzufügen:**
   ```kotlin
   // 1. Screen erstellen in ui/screens/
   @Composable
   fun NewScreen() { ... }
   
   // 2. Route in NavigationDestination.kt hinzufügen
   object New : NavigationDestination(
       route = "new",
       title = "Neu",
       icon = Icons.Default.YourIcon
   )
   
   // 3. Route in AppNavigation.kt registrieren
   composable(NavigationDestination.New.route) {
       NewScreen()
   }
   ```

## 📝 Verwendung

### Theme ändern

Die App unterstützt drei Theme-Modi:
- **System** - Folgt den System-Einstellungen
- **Hell** - Helles Theme
- **Dunkel** - Dunkles Theme

Einstellbar unter: Einstellungen → Darstellung → Theme

### Navigation

Die App verwendet zwei Navigationselemente:

**Bottom Navigation:**
- Startseite
- Dashboard
- Profil

**Navigation Drawer (Seitenmenü):**
- Startseite
- Dashboard
- Profil
- Einstellungen
- Über

### Einstellungen erweitern

Neue Einstellungen in `UserPreferencesRepository.kt` hinzufügen:

```kotlin
companion object {
    private val NEW_SETTING_KEY = booleanPreferencesKey("new_setting")
}

val newSetting: Flow<Boolean> = context.dataStore.data.map { preferences ->
    preferences[NEW_SETTING_KEY] ?: false
}

suspend fun setNewSetting(value: Boolean) {
    context.dataStore.edit { preferences ->
        preferences[NEW_SETTING_KEY] = value
    }
}
```

## 🔧 Konfiguration

### Build-Varianten

Definiert in `app/build.gradle.kts`:
- **debug** - Debug-Build mit Logging
- **release** - Release-Build optimiert

### ProGuard

ProGuard-Regeln in `proguard-rules.pro` anpassen für Release-Builds.

## 📚 Dokumentation

Alle Klassen und Funktionen sind ausführlich dokumentiert mit:
- KDoc-Kommentaren
- Funktionsbeschreibungen
- Parameter-Erklärungen

Siehe auch: [ARCHITECTURE.md](ARCHITECTURE.md) für detaillierte Architektur-Dokumentation.

## 🤝 Beitragen

Dieses Template kann frei verwendet und angepasst werden. Verbesserungsvorschläge sind willkommen!

## 📄 Lizenz

MIT License

Copyright (c) 2026

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## 👨‍💻 Autor

Erstellt als Template für Android-Entwicklung

## 🔗 Links

- [Jetpack Compose Dokumentation](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- [Navigation Component](https://developer.android.com/guide/navigation)
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)

---

**Viel Erfolg mit Ihrem Android-Projekt! 🚀**