package com.example.tugas3.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tugas3.components.NoteItem
import com.example.tugas3.data.Note
import com.example.tugas3.data.SortOrder
import com.example.tugas3.viewmodel.NotesUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    uiState: NotesUiState,
    searchQuery: String,
    isOnline: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onSortOrderChange: (SortOrder) -> Unit,
    onNoteClick: (Long) -> Unit,
    onEditClick: (Long) -> Unit,
    onFavoriteToggle: (Note) -> Unit,
    modifier: Modifier = Modifier
) {
    var showSortMenu by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = !isOnline,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.CloudOff,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Anda sedang offline",
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

        SearchBar(
            query = searchQuery,
            onQueryChange = onSearchQueryChange,
            onSearch = {},
            active = false,
            onActiveChange = {},
            placeholder = { Text("Cari catatan...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { showSortMenu = true }) {
                    Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = "Urutkan")
                }
                DropdownMenu(
                    expanded = showSortMenu,
                    onDismissRequest = { showSortMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Terbaru") },
                        onClick = { onSortOrderChange(SortOrder.NEWEST); showSortMenu = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Terlama") },
                        onClick = { onSortOrderChange(SortOrder.OLDEST); showSortMenu = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Judul (A-Z)") },
                        onClick = { onSortOrderChange(SortOrder.TITLE); showSortMenu = false }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {}

        when (uiState) {
            is NotesUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is NotesUiState.Empty -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Catatan tidak ditemukan")
                }
            }
            is NotesUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.notes, key = { it.id }) { note ->
                        NoteItem(
                            note = note,
                            onClick = { onNoteClick(note.id) },
                            onEditClick = { onEditClick(note.id) },
                            onFavoriteToggle = { onFavoriteToggle(note) }
                        )
                    }
                }
            }
        }
    }
}
