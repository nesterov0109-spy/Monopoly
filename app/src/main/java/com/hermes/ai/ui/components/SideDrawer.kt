package com.hermes.ai.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SideDrawerContent(onClose: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface).padding(24.dp).verticalScroll(rememberScrollState())
    ) {
        Text("Hermes AI", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        Spacer(Modifier.height(24.dp))
        listOf("New Chat", "History", "Clear", "Settings", "About").forEach { item ->
            Text(item, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.fillMaxWidth().clickable { }.padding(vertical = 12.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
