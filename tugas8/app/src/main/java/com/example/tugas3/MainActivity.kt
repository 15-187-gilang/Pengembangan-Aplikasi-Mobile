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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.tugas3.data.SettingsManager
import com.example.tugas3.navigation.Screen
import com.example.tugas3.platform.DeviceInfo
import com.example.tugas3.screens.*
import com.example.tugas3.ui.theme.Tugas3Theme
import com.example.tugas3.viewmodel.NoteViewModel
import com.example.tugas3.viewmodel.NotesUiState
import com.example.tugas3.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    
    private val settingsManager: SettingsManager by inject()
    private val deviceInfo: DeviceInfo by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        enableEdgeToEdge()
        setContent {
            KoinContext {
                val noteViewModel: NoteViewModel = koinViewModel()
                val profileViewModel: ProfileViewModel = koinViewModel()
                
                val isDarkMode by settingsManager.isDarkMode.collectAsState(initial = false)
                val sortOrder by settingsManager.sortOrder.collectAsState(initial = com.example.tugas3.data.SortOrder.NEWEST)
                val profileUiState by profileViewModel.uiState.collectAsState()
                val notesUiState by noteViewModel.uiState.collectAsState()
                val searchQuery by noteViewModel.searchQuery.collectAsState()
                val isOnline by noteViewModel.isOnline.collectAsState()
                val scope = rememberCoroutineScope()

                Tugas3Theme(darkTheme = isDarkMode) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    val bottomBarScreens = listOf(Screen.Notes, Screen.Favorites, Screen.Profile, Screen.Settings)
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
                                    uiState = notesUiState,
                                    searchQuery = searchQuery,
                                    isOnline = isOnline,
                                    onSearchQueryChange = { noteViewModel.onSearchQueryChange(it) },
                                    onSortOrderChange = { noteViewModel.setSortOrder(it) },
                                    onNoteClick = { id -> navController.navigate(Screen.NoteDetail.createRoute(id)) },
                                    onEditClick = { id -> navController.navigate(Screen.EditNote.createRoute(id)) },
                                    onFavoriteToggle = { note -> noteViewModel.toggleFavorite(note) }
                                )
                            }
                            composable(Screen.Favorites.route) {
                                val favoriteUiState = if (notesUiState is NotesUiState.Success) {
                                    val favs = (notesUiState as NotesUiState.Success).notes.filter { it.isFavorite }
                                    if (favs.isEmpty()) NotesUiState.Empty else NotesUiState.Success(favs)
                                } else notesUiState

                                NoteListScreen(
                                    uiState = favoriteUiState,
                                    searchQuery = searchQuery,
                                    isOnline = isOnline,
                                    onSearchQueryChange = { noteViewModel.onSearchQueryChange(it) },
                                    onSortOrderChange = { noteViewModel.setSortOrder(it) },
                                    onNoteClick = { id -> navController.navigate(Screen.NoteDetail.createRoute(id)) },
                                    onEditClick = { id -> navController.navigate(Screen.EditNote.createRoute(id)) },
                                    onFavoriteToggle = { note -> noteViewModel.toggleFavorite(note) }
                                )
                            }
                            composable(Screen.Profile.route) {
                                ProfileScreen(
                                    uiState = profileUiState.copy(isDarkMode = isDarkMode),
                                    onUpdateProfile = { name, bio -> profileViewModel.updateProfile(name, bio) },
                                    onToggleDarkMode = { scope.launch { settingsManager.setDarkMode(it) } },
                                    modifier = Modifier
                                )
                            }
                            composable(Screen.Settings.route) {
                                SettingsScreen(
                                    isDarkMode = isDarkMode,
                                    onDarkModeToggle = { scope.launch { settingsManager.setDarkMode(it) } },
                                    sortOrder = sortOrder,
                                    onSortOrderChange = { noteViewModel.setSortOrder(it) },
                                    deviceInfo = deviceInfo
                                )
                            }
                            composable(
                                route = Screen.NoteDetail.route,
                                arguments = listOf(navArgument("noteId") { type = NavType.LongType })
                            ) { backStackEntry ->
                                val noteId = backStackEntry.arguments?.getLong("noteId") ?: -1L
                                val note by noteViewModel.getNoteById(noteId).collectAsState(initial = null)
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
                                arguments = listOf(navArgument("noteId") { type = NavType.LongType })
                            ) { backStackEntry ->
                                val noteId = backStackEntry.arguments?.getLong("noteId") ?: -1L
                                val note by noteViewModel.getNoteById(noteId).collectAsState(initial = null)
                                AddEditNoteScreen(
                                    note = note,
                                    onSave = { title, content ->
                                        note?.let {
                                            noteViewModel.updateNote(noteId, title, content, it.isFavorite, it.createdAt)
                                        }
                                        navController.popBackStack()
                                    },
                                    onDelete = {
                                        noteViewModel.deleteNote(noteId)
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
}
