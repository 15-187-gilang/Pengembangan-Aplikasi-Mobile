package com.example.tugas3.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tugas3.components.NoteItem
import com.example.tugas3.data.Note

@Composable
fun NoteListScreen(
    notes: List<Note>,
    onNoteClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onFavoriteToggle: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (notes.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Tidak ada catatan")
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notes) { note ->
                NoteItem(
                    note = note,
                    onClick = { onNoteClick(note.id) },
                    onEditClick = { onEditClick(note.id) },
                    onFavoriteToggle = { onFavoriteToggle(note.id) }
                )
            }
        }
    }
}
