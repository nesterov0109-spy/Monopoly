package com.hermes.ai.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class BottomNavItem(val title: String, val icon: ImageVector, val route: String)

val bottomNavItems = listOf(
    BottomNavItem("Chat", Icons.Filled.Home, "chat"),
    BottomNavItem("History", Icons.Filled.Star, "history"),
    BottomNavItem("Settings", Icons.Filled.Settings, "settings")
)

@Composable
fun BottomNavBar(selectedItem: String, onItemSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surface, modifier = modifier.fillMaxWidth()) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = selectedItem == item.route,
                onClick = { onItemSelected(item.route) },
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.primary.copy(0.15f))
            )
        }
    }
}
