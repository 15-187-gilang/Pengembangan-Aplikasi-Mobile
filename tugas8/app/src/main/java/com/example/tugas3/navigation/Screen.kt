package com.example.tugas3.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector? = null) {
    object Notes : Screen("notes", "Notes", Icons.AutoMirrored.Filled.Notes)
    object Favorites : Screen("favorites", "Favorites", Icons.Default.Favorite)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
    object NoteDetail : Screen("note_detail/{noteId}", "Detail") {
        fun createRoute(noteId: Long) = "note_detail/$noteId"
    }
    object AddNote : Screen("add_note", "Add Note")
    object EditNote : Screen("edit_note/{noteId}", "Edit Note") {
        fun createRoute(noteId: Long) = "edit_note/$noteId"
    }
}
