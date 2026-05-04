package com.example.tugas3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas3.data.Note
import com.example.tugas3.data.NoteRepository
import com.example.tugas3.data.SettingsManager
import com.example.tugas3.data.SortOrder
import com.example.tugas3.platform.NetworkMonitor
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class NotesUiState {
    object Loading : NotesUiState()
    data class Success(val notes: List<Note>) : NotesUiState()
    object Empty : NotesUiState()
}

class NoteViewModel(
    private val repository: NoteRepository,
    private val settingsManager: SettingsManager,
    networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val isOnline: StateFlow<Boolean> = networkMonitor.isOnline
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )

    val uiState: StateFlow<NotesUiState> = combine(
        _searchQuery,
        settingsManager.sortOrder
    ) { query, sortOrder ->
        query to sortOrder
    }.flatMapLatest { (query, sortOrder) ->
        val flow = if (query.isEmpty()) {
            repository.getAllNotes()
        } else {
            repository.searchNotes(query)
        }
        
        flow.map { notes ->
            val sortedNotes = when (sortOrder) {
                SortOrder.NEWEST -> notes.sortedByDescending { it.createdAt }
                SortOrder.OLDEST -> notes.sortedBy { it.createdAt }
                SortOrder.TITLE -> notes.sortedBy { it.title }
            }
            if (sortedNotes.isEmpty()) NotesUiState.Empty else NotesUiState.Success(sortedNotes)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = NotesUiState.Loading
    )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repository.insertNote(title, content)
        }
    }

    fun updateNote(id: Long, title: String, content: String, isFavorite: Boolean, createdAt: Long) {
        viewModelScope.launch {
            repository.updateNote(id, title, content, isFavorite, createdAt)
        }
    }

    fun toggleFavorite(note: Note) {
        viewModelScope.launch {
            repository.toggleFavorite(note.id, note.isFavorite)
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }

    fun getNoteById(id: Long): Flow<Note?> {
        return repository.getNoteById(id)
    }
    
    fun setSortOrder(order: SortOrder) {
        viewModelScope.launch {
            settingsManager.setSortOrder(order)
        }
    }
}
