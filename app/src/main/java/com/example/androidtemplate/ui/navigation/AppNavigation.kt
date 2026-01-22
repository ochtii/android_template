package com.example.androidtemplate.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidtemplate.ui.screens.about.AboutScreen
import com.example.androidtemplate.ui.screens.dashboard.DashboardScreen
import com.example.androidtemplate.ui.screens.home.HomeScreen
import com.example.androidtemplate.ui.screens.profile.ProfileScreen
import com.example.androidtemplate.ui.screens.settings.SettingsScreen
import kotlinx.coroutines.launch

/**
 * Haupt-Navigation der App
 * 
 * Kombiniert Navigation Drawer und Bottom Navigation.
 * Verwendet Jetpack Navigation Component für die Navigation zwischen Screens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawerContent(
                currentRoute = currentRoute,
                onNavigate = { destination ->
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(destination.route) {
                        // Pop up to start destination to avoid building a large back stack
                        popUpTo(NavigationDestination.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    currentRoute = currentRoute,
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            bottomBar = {
                // Bottom Navigation nur für bestimmte Screens anzeigen
                if (currentRoute in bottomNavigationItems.map { it.route }) {
                    AppBottomNavigation(
                        currentRoute = currentRoute,
                        onNavigate = { destination ->
                            navController.navigate(destination.route) {
                                popUpTo(NavigationDestination.Home.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            NavigationGraph(
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

/**
 * Navigation Graph
 * 
 * Definiert alle verfügbaren Screens und ihre Routes.
 */
@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Home.route,
        modifier = modifier
    ) {
        composable(NavigationDestination.Home.route) {
            HomeScreen()
        }
        composable(NavigationDestination.Dashboard.route) {
            DashboardScreen()
        }
        composable(NavigationDestination.Profile.route) {
            ProfileScreen()
        }
        composable(NavigationDestination.Settings.route) {
            SettingsScreen()
        }
        composable(NavigationDestination.About.route) {
            AboutScreen()
        }
    }
}
