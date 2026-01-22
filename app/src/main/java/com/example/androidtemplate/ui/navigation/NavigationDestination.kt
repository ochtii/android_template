package com.example.androidtemplate.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Navigation Destinations
 * 
 * Sealed class für typsichere Navigation zwischen Screens.
 * Jede Destination repräsentiert einen Screen in der App.
 */
sealed class NavigationDestination(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavigationDestination(
        route = "home",
        title = "Startseite",
        icon = Icons.Default.Home
    )

    object Dashboard : NavigationDestination(
        route = "dashboard",
        title = "Dashboard",
        icon = Icons.Default.Home
    )

    object Profile : NavigationDestination(
        route = "profile",
        title = "Profil",
        icon = Icons.Default.Person
    )

    object Settings : NavigationDestination(
        route = "settings",
        title = "Einstellungen",
        icon = Icons.Default.Settings
    )

    object About : NavigationDestination(
        route = "about",
        title = "Über",
        icon = Icons.Default.Info
    )
}

/**
 * Liste der Bottom Navigation Items
 * Diese Screens werden in der Bottom Navigation Bar angezeigt
 */
val bottomNavigationItems = listOf(
    NavigationDestination.Home,
    NavigationDestination.Dashboard,
    NavigationDestination.Profile
)

/**
 * Liste der Drawer Navigation Items
 * Diese Screens werden im Navigation Drawer angezeigt
 */
val drawerNavigationItems = listOf(
    NavigationDestination.Home,
    NavigationDestination.Dashboard,
    NavigationDestination.Profile,
    NavigationDestination.Settings,
    NavigationDestination.About
)
