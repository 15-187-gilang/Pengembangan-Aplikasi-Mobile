package com.example.tugas3.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.example.tugas3.database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepository(db: NoteDatabase) {
    private val queries = db.noteDatabaseQueries

    fun getAllNotes(): Flow<List<Note>> {
        return queries.getAllNotes()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { entities ->
                entities.map { it.toNote() }
            }
    }

    fun searchNotes(query: String): Flow<List<Note>> {
        return queries.searchNotes(query)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { entities ->
                entities.map { it.toNote() }
            }
    }

    suspend fun insertNote(title: String, content: String) {
        queries.insertNote(
            id = null,
            title = title,
            content = content,
            isFavorite = 0L,
            createdAt = System.currentTimeMillis()
        )
    }

    suspend fun updateNote(id: Long, title: String, content: String, isFavorite: Boolean, createdAt: Long) {
        queries.insertNote(
            id = id,
            title = title,
            content = content,
            isFavorite = if (isFavorite) 1L else 0L,
            createdAt = createdAt
        )
    }

    suspend fun deleteNote(id: Long) {
        queries.deleteNote(id)
    }

    suspend fun toggleFavorite(id: Long, isFavorite: Boolean) {
        queries.updateFavorite(isFavorite = if (!isFavorite) 1L else 0L, id = id)
    }

    fun getNoteById(id: Long): Flow<Note?> {
        return queries.getNoteById(id)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
            .map { it?.toNote() }
    }
}

private fun com.example.tugas3.database.NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        isFavorite = isFavorite != 0L,
        createdAt = createdAt
    )
}
