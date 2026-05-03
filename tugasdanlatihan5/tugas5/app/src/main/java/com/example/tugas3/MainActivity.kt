package com.example.tugas3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.tugas3.navigation.Screen
import com.example.tugas3.screens.*
import com.example.tugas3.ui.theme.Tugas3Theme
import com.example.tugas3.viewmodel.NoteViewModel
import com.example.tugas3.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val profileViewModel: ProfileViewModel = viewModel()
            val noteViewModel: NoteViewModel = viewModel()
            val profileUiState by profileViewModel.uiState.collectAsState()
            val notes by noteViewModel.notes.collectAsState()

            Tugas3Theme(darkTheme = profileUiState.isDarkMode) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val bottomBarScreens = listOf(Screen.Notes, Screen.Favorites, Screen.Profile)
                val showBottomBar = currentDestination?.route in bottomBarScreens.map { it.route }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar {
                                bottomBarScreens.forEach { screen ->
                                    NavigationBarItem(
                                        icon = { Icon(screen.icon!!, contentDescription = screen.label) },
                                        label = { Text(screen.label) },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    },
                    floatingActionButton = {
                        if (currentDestination?.route == Screen.Notes.route) {
                            FloatingActionButton(onClick = { navController.navigate(Screen.AddNote.route) }) {
                                Icon(Icons.Default.Add, contentDescription = "Add Note")
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Notes.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Notes.route) {
                            NoteListScreen(
                                notes = notes,
                                onNoteClick = { id -> navController.navigate(Screen.NoteDetail.createRoute(id)) },
                                onEditClick = { id -> navController.navigate(Screen.EditNote.createRoute(id)) },
                                onFavoriteToggle = { id -> noteViewModel.toggleFavorite(id) }
                            )
                        }
                        composable(Screen.Favorites.route) {
                            NoteListScreen(
                                notes = notes.filter { it.isFavorite },
                                onNoteClick = { id -> navController.navigate(Screen.NoteDetail.createRoute(id)) },
                                onEditClick = { id -> navController.navigate(Screen.EditNote.createRoute(id)) },
                                onFavoriteToggle = { id -> noteViewModel.toggleFavorite(id) }
                            )
                        }
                        composable(Screen.Profile.route) {
                            ProfileScreen(
                                uiState = profileUiState,
                                onUpdateProfile = { name, bio -> profileViewModel.updateProfile(name, bio) },
                                onToggleDarkMode = { profileViewModel.toggleDarkMode(it) }
                            )
                        }
                        composable(
                            route = Screen.NoteDetail.route,
                            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
                            val note = noteViewModel.getNoteById(noteId)
                            NoteDetailScreen(
                                note = note,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                        composable(Screen.AddNote.route) {
                            AddEditNoteScreen(
                                note = null,
                                onSave = { title, content ->
                                    noteViewModel.addNote(title, content)
                                    navController.popBackStack()
                                },
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                        composable(
                            route = Screen.EditNote.route,
                            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
                            val note = noteViewModel.getNoteById(noteId)
                            AddEditNoteScreen(
                                note = note,
                                onSave = { title, content ->
                                    noteViewModel.updateNote(noteId, title, content)
                                    navController.popBackStack()
                                },
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
