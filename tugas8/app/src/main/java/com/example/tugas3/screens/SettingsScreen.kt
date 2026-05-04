package com.example.tugas3.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tugas3.data.SortOrder
import com.example.tugas3.platform.DeviceInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkMode: Boolean,
    onDarkModeToggle: (Boolean) -> Unit,
    sortOrder: SortOrder,
    onSortOrderChange: (SortOrder) -> Unit,
    deviceInfo: DeviceInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        // Dark Mode Setting
        Card(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DarkMode, contentDescription = null)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Dark Mode")
                }
                Switch(checked = isDarkMode, onCheckedChange = onDarkModeToggle)
            }
        }

        // Sort Order Setting
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Sort, contentDescription = null)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Sort Order")
                }
                Spacer(modifier = Modifier.height(8.dp))
                
                SortOrder.entries.forEach { order ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = sortOrder == order,
                            onClick = { onSortOrderChange(order) }
                        )
                        Text(
                            text = when(order) {
                                SortOrder.NEWEST -> "Newest First"
                                SortOrder.OLDEST -> "Oldest First"
                                SortOrder.TITLE -> "Title (A-Z)"
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }

        // Device Info Section
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = null)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Device Information", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.height(16.dp))
                
                DeviceInfoItem("Manufacturer", deviceInfo.manufacturer)
                DeviceInfoItem("Model", deviceInfo.model)
                DeviceInfoItem("Android Version", deviceInfo.androidVersion)
                DeviceInfoItem("SDK Version", deviceInfo.sdkVersion.toString())
            }
        }
    }
}

@Composable
private fun DeviceInfoItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
    }
}
