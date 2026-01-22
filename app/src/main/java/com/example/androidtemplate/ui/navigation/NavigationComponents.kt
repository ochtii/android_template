package com.example.androidtemplate.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Top App Bar mit Menu Button
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentRoute: String?,
    onMenuClick: () -> Unit
) {
    val currentDestination = drawerNavigationItems.find { it.route == currentRoute }
    
    TopAppBar(
        title = {
            Text(text = currentDestination?.title ?: "Android Template")
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menü öffnen"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

/**
 * Bottom Navigation Bar
 */
@Composable
fun AppBottomNavigation(
    currentRoute: String?,
    onNavigate: (NavigationDestination) -> Unit
) {
    NavigationBar {
        bottomNavigationItems.forEach { destination ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.title
                    )
                },
                label = { Text(destination.title) },
                selected = currentRoute == destination.route,
                onClick = { onNavigate(destination) }
            )
        }
    }
}

/**
 * Navigation Drawer Content
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerContent(
    currentRoute: String?,
    onNavigate: (NavigationDestination) -> Unit
) {
    ModalDrawerSheet {
        // Drawer Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                Text(
                    text = "Android Template",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Version 1.0.0",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Divider()

        // Navigation Items
        drawerNavigationItems.forEach { destination ->
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.title
                    )
                },
                label = { Text(destination.title) },
                selected = currentRoute == destination.route,
                onClick = { onNavigate(destination) },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}
