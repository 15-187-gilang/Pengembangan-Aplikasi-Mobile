package com.example.latihan2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.latihan2.ui.theme.Latihan2Theme

// 1. Sealed class untuk routes (Checklist: Sealed class untuk routes)
sealed class Screen(val route: String) {
    data object NoteList : Screen("note_list")
    // Checklist: Route dengan {noteId}
    data object NoteDetail : Screen("note_detail/{noteId}") {
        fun createRoute(noteId: Int) = "note_detail/$noteId"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Latihan2Theme {
                NoteApp()
            }
        }
    }
}

@Composable
fun NoteApp() {
    val navController = rememberNavController()

    // 2. NavHost setup
    NavHost(
        navController = navController,
        startDestination = Screen.NoteList.route
    ) {
        composable(route = Screen.NoteList.route) {
            // Checklist: NoteListScreen dengan clickable list items
            NoteListScreen(
                onNoteClick = { noteId ->
                    // Checklist: navigate dengan noteId
                    navController.navigate(Screen.NoteDetail.createRoute(noteId))
                }
            )
        }
        composable(
            route = Screen.NoteDetail.route,
            // Checklist: navArgument setup
            arguments = listOf(
                navArgument("noteId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            // Checklist: NoteDetailScreen menampilkan noteId
            NoteDetailScreen(
                noteId = noteId,
                // Checklist: Back navigation
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(onNoteClick: (Int) -> Unit) {
    val notes = (1..15).toList()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Note List") })
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(notes) { noteId ->
                NoteItem(noteId = noteId, onClick = { onNoteClick(noteId) })
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun NoteItem(noteId: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(text = "Note #$noteId", style = MaterialTheme.typography.titleMedium)
        Text(text = "Ketuk untuk melihat detail", style = MaterialTheme.typography.bodySmall)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(noteId: Int, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Note Detail") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Menampilkan detail untuk Note ID: $noteId",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Halaman ini menampilkan informasi mendetail untuk catatan dengan ID $noteId sesuai instruksi latihan.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
