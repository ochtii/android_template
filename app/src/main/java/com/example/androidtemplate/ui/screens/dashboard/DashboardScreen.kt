package com.example.androidtemplate.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Dashboard Screen
 * 
 * Demo-Dashboard mit Statistik-Karten.
 * Kann für echte Daten und Visualisierungen erweitert werden.
 */
@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "Hier können Sie Ihre Dashboard-Widgets hinzufügen.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Statistik-Karten
        DashboardCard(
            icon = Icons.Default.TrendingUp,
            title = "Statistik 1",
            value = "1,234",
            description = "Gesamtanzahl",
            color = MaterialTheme.colorScheme.primaryContainer
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        DashboardCard(
            icon = Icons.Default.BarChart,
            title = "Statistik 2",
            value = "567",
            description = "Aktive Einträge",
            color = MaterialTheme.colorScheme.secondaryContainer
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        DashboardCard(
            icon = Icons.Default.ShowChart,
            title = "Statistik 3",
            value = "89%",
            description = "Erfolgsrate",
            color = MaterialTheme.colorScheme.tertiaryContainer
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Info Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "💡 Tipp",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Fügen Sie hier Ihre eigenen Widgets und Visualisierungen hinzu. Nutzen Sie Chart-Libraries wie MPAndroidChart oder Vico für professionelle Diagramme.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

/**
 * Dashboard-Karte mit Icon und Statistik
 */
@Composable
fun DashboardCard(
    icon: ImageVector,
    title: String,
    value: String,
    description: String,
    color: androidx.compose.ui.graphics.Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
