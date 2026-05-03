package com.example.tugas3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tugas3.data.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteViewModel : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(
        listOf(
            Note(1, "Catatan 1", "Isi catatan 1"),
            Note(2, "Catatan 2", "Isi catatan 2", true),
            Note(3, "Catatan 3", "Isi catatan 3")
        )
    )
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    fun addNote(title: String, content: String) {
        _notes.update { currentNotes ->
            val newId = (currentNotes.maxOfOrNull { it.id } ?: 0) + 1
            currentNotes + Note(newId, title, content)
        }
    }

    fun updateNote(id: Int, title: String, content: String) {
        _notes.update { currentNotes ->
            currentNotes.map {
                if (it.id == id) it.copy(title = title, content = content) else it
            }
        }
    }

    fun toggleFavorite(id: Int) {
        _notes.update { currentNotes ->
            currentNotes.map {
                if (it.id == id) it.copy(isFavorite = !it.isFavorite) else it
            }
        }
    }

    fun deleteNote(id: Int) {
        _notes.update { currentNotes ->
            currentNotes.filter { it.id != id }
        }
    }

    fun getNoteById(id: Int): Note? {
        return _notes.value.find { it.id == id }
    }
}
