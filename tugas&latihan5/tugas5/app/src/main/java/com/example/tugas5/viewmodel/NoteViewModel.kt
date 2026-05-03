package com.example.tugas5.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tugas5.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteViewModel : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private var nextId = 1

    init {
        // Sample data
        addNote("Welcome", "This is your first note!")
        addNote("Shopping List", "Milk, Eggs, Bread")
    }

    fun addNote(title: String, content: String) {
        val newNote = Note(id = nextId++, title = title, content = content)
        _notes.update { it + newNote }
    }

    fun updateNote(id: Int, title: String, content: String) {
        _notes.update { list ->
            list.map { if (it.id == id) it.copy(title = title, content = content) else it }
        }
    }

    fun deleteNote(id: Int) {
        _notes.update { list -> list.filter { it.id != id } }
    }

    fun toggleFavorite(id: Int) {
        _notes.update { list ->
            list.map { if (it.id == id) it.copy(isFavorite = !it.isFavorite) else it }
        }
    }

    fun getNoteById(id: Int): Note? {
        return _notes.value.find { it.id == id }
    }
}
