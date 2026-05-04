package com.example.tugas3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tugas3.data.NoteRepository
import com.example.tugas3.data.SettingsManager
import com.example.tugas3.platform.NetworkMonitor

class NoteViewModelFactory(
    private val repository: NoteRepository,
    private val settingsManager: SettingsManager,
    private val networkMonitor: NetworkMonitor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository, settingsManager, networkMonitor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
