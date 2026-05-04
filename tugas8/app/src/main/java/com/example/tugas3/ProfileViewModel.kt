package com.example.tugas3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProfileUiState(
    val name: String = "Gilang Surya Agung",
    val bio: String = "Mahasiswa Teknik Informatika Institut Teknologi Sumatera (ITERA). Antusias dalam pengembangan aplikasi mobile dan desain UI/UX.",
    val email: String = "gilang.123140187@student.itera.ac.id",
    val phone: String = "085353341688",
    val location: String = "Bandar Lampung, Indonesia",
    val isDarkMode: Boolean = false
)

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun updateProfile(name: String, bio: String) {
        _uiState.update { it.copy(name = name, bio = bio) }
    }

    fun toggleDarkMode(isDark: Boolean) {
        _uiState.update { it.copy(isDarkMode = isDark) }
    }
}
