# Architektur-Dokumentation

## Überblick

Diese Android-App folgt einer klaren, modularen Architektur, die auf modernen Android-Entwicklungsstandards basiert.

## Architektur-Schichten

### 1. Presentation Layer (UI)

**Verantwortlich für:** Darstellung und Benutzerinteraktion

```
ui/
├── navigation/          # Navigation-Logik
├── screens/            # UI-Screens (Composables)
├── theme/              # Theme und Styling
└── MainActivity.kt     # Haupt-Activity
```

**Technologien:**
- Jetpack Compose für deklarative UI
- Material Design 3 Komponenten
- Navigation Component

**Prinzipien:**
- Jeder Screen ist ein eigenständiges Composable
- State wird über Flow/StateFlow verwaltet
- UI-Logik ist von Business-Logik getrennt

### 2. Data Layer

**Verantwortlich für:** Datenverwaltung und Persistenz

```
data/
└── preferences/        # Einstellungen-Repository
```

**Technologien:**
- DataStore für Preferences
- Coroutines Flow für reaktive Datenströme

**Prinzipien:**
- Repository-Pattern
- Single Source of Truth
- Asynchrone Operationen mit Coroutines

### 3. Application Layer

**Verantwortlich für:** App-weite Initialisierung

```
TemplateApplication.kt  # Application-Klasse
```

## Datenfluss

```
User Input
    ↓
Composable (UI)
    ↓
Event Handler
    ↓
Repository
    ↓
DataStore
    ↓
Flow/State Update
    ↓
UI Recomposition
```

## Navigation-Architektur

### Navigation Drawer + Bottom Navigation

Die App verwendet ein hybrides Navigationskonzept:

1. **Bottom Navigation**: Für Haupt-Screens (Home, Dashboard, Profil)
2. **Navigation Drawer**: Für alle Screens inkl. Einstellungen

### Navigation Flow

```kotlin
NavigationDestination (sealed class)
    ↓
AppNavigation (NavHost)
    ↓
Screen Composables
```

## Theme-System

### Dark/Light Mode

Das Theme-System unterstützt:
- System-Theme folgen
- Manuell Hell/Dunkel wählen
- Persistente Speicherung in DataStore

```
User selects theme
    ↓
SettingsScreen
    ↓
UserPreferencesRepository.setThemeMode()
    ↓
DataStore update
    ↓
Flow emission
    ↓
MainActivity recomposition
    ↓
Theme applied
```

## State Management

### Compose State

- `remember` für lokalen State
- `collectAsState()` für Flow → State Konvertierung
- `ViewModel` (optional für komplexere Screens)

### DataStore Flow

```kotlin
Flow<Preferences>
    ↓
map { ... }
    ↓
Flow<T>
    ↓
collectAsState()
    ↓
State<T>
```

## Erweiterbarkeit

### Neue Screens hinzufügen

1. **Screen erstellen:**
```kotlin
// ui/screens/newfeature/NewFeatureScreen.kt
@Composable
fun NewFeatureScreen() {
    // Implementation
}
```

2. **Navigation hinzufügen:**
```kotlin
// ui/navigation/NavigationDestination.kt
object NewFeature : NavigationDestination(
    route = "new_feature",
    title = "Neues Feature",
    icon = Icons.Default.Star
)
```

3. **Route registrieren:**
```kotlin
// ui/navigation/AppNavigation.kt
composable(NavigationDestination.NewFeature.route) {
    NewFeatureScreen()
}
```

### Neue Einstellungen hinzufügen

1. **Preference Key definieren:**
```kotlin
private val NEW_SETTING_KEY = stringPreferencesKey("new_setting")
```

2. **Flow erstellen:**
```kotlin
val newSetting: Flow<String> = context.dataStore.data.map { 
    preferences -> preferences[NEW_SETTING_KEY] ?: "default"
}
```

3. **Setter-Funktion:**
```kotlin
suspend fun setNewSetting(value: String) {
    context.dataStore.edit { preferences ->
        preferences[NEW_SETTING_KEY] = value
    }
}
```

### ViewModels hinzufügen (optional)

Für komplexere Screens:

```kotlin
class MyViewModel(
    private val repository: UserPreferencesRepository
) : ViewModel() {
    
    val uiState: StateFlow<MyUiState> = repository.data
        .map { MyUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MyUiState()
        )
    
    fun onEvent(event: MyEvent) {
        // Handle events
    }
}
```

## Best Practices

### 1. Composable Functions

- Klein und fokussiert halten
- Wiederverwendbar gestalten
- State hoisting anwenden
- Preview-Annotationen verwenden

### 2. State Management

- Unidirektionaler Datenfluss
- State nur wenn nötig
- Immutable State bevorzugen

### 3. Performance

- `remember` für teure Berechnungen
- `LazyColumn/Row` für Listen
- `derivedStateOf` für berechnete States
- Recomposition minimieren

### 4. Navigation

- Typsichere Navigation mit sealed class
- State bei Navigation speichern
- Deep Links vorbereiten (optional)

### 5. Theming

- Material Design 3 Guidelines folgen
- Konsistente Farben und Typography
- Dark Mode testen

## Testing-Strategie

### UI Tests (Compose)

```kotlin
@Test
fun homeScreen_displaysContent() {
    composeTestRule.setContent {
        HomeScreen()
    }
    composeTestRule.onNodeWithText("Willkommen").assertIsDisplayed()
}
```

### Repository Tests

```kotlin
@Test
fun userPreferences_themeModeUpdate() = runTest {
    repository.setThemeMode(AppCompatDelegate.MODE_NIGHT_YES)
    val mode = repository.themeMode.first()
    assertEquals(AppCompatDelegate.MODE_NIGHT_YES, mode)
}
```

## Abhängigkeiten-Management

### Version Catalog (empfohlen)

Für größere Projekte:

```toml
[versions]
compose = "1.5.4"
navigation = "2.7.6"

[libraries]
compose-ui = { module = "androidx.compose.ui:ui", version.ref = "compose" }
navigation-compose = { module = "androidx.navigation:navigation-compose", version.ref = "navigation" }
```

## Deployment

### Release Build

1. Signing-Konfiguration in `build.gradle.kts`
2. ProGuard-Regeln prüfen
3. Version-Code/Name erhöhen
4. Release-Build erstellen

```bash
./gradlew assembleRelease
```

## Weitere Ressourcen

- [Compose Best Practices](https://developer.android.com/jetpack/compose/performance)
- [Repository Pattern](https://developer.android.com/topic/architecture/data-layer)
- [Navigation Component Guide](https://developer.android.com/guide/navigation)
