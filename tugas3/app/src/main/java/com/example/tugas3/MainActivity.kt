package com.example.tugas3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tugas3.ui.theme.Tugas3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tugas3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Memanggil fungsi utama ProfileScreen dengan data terbaru
                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Composable utama untuk menampilkan halaman profil secara utuh.
 */
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bagian Header Profil dengan nama Gilang Surya Agung dan logo yang dipercantik
        ProfileHeader(
            name = "Gilang Surya Agung",
            bio = "Mahasiswa Teknik Informatika Institut Teknologi Sumatera (ITERA). Antusias dalam pengembangan aplikasi mobile dan desain UI/UX."
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Bagian Informasi Detail dalam Card dengan data terbaru sesuai permintaan
        ProfileCard {
            InfoItem(
                icon = Icons.Default.Email,
                label = "Email",
                value = "gilang.123140187@student.itera.ac.id"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp)
            InfoItem(
                icon = Icons.Default.Phone,
                label = "Phone",
                value = "085353341688"
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp)
            InfoItem(
                icon = Icons.Default.LocationOn,
                label = "Location",
                value = "Bandar Lampung, Indonesia"
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol interaksi
        Button(
            onClick = { /* Aksi edit profil */ },
            modifier = Modifier.fillMaxWidth(0.7f),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Edit Profile")
        }
    }
}

/**
 * Reusable Composable untuk bagian Header: Logo profil (circular dengan gradient) dan Nama.
 */
@Composable
fun ProfileHeader(name: String, bio: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Kontainer Logo Circular dengan desain yang lebih bagus (Gradient + Border)
        Box(
            modifier = Modifier
                .size(130.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.secondaryContainer
                        )
                    )
                )
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Menggunakan Icon AccountCircle sebagai logo yang elegan
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Picture",
                modifier = Modifier.fillMaxSize(0.85f),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nama Pengguna
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Deskripsi Singkat / Bio
        Text(
            text = bio,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

/**
 * Reusable Composable untuk menampilkan satu baris informasi (Email, Phone, dll).
 */
@Composable
fun InfoItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Lingkaran kecil di belakang icon untuk estetika
        Surface(
            modifier = Modifier.size(42.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

/**
 * Reusable Composable Card untuk membungkus konten informasi agar terlihat rapi.
 */
@Composable
fun ProfileCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

/**
 * Fungsi Preview untuk melihat tampilan di Android Studio.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePreview() {
    Tugas3Theme {
        ProfileScreen()
    }
}
