# Profile App - Tugas 4

Aplikasi Profil Sederhana yang dikembangkan menggunakan Jetpack Compose dengan arsitektur MVVM (Model-View-ViewModel).

## Fitur Utama

1.  **Arsitektur MVVM**: Pemisahan logika bisnis dan UI menggunakan `ViewModel` dan `StateFlow` untuk manajemen state yang reaktif.
2.  **Edit Profile**: Fitur untuk mengubah Nama dan Bio pengguna dengan implementasi *state hoisting* pada TextField.
3.  **Dark Mode Toggle**: Fitur untuk beralih antara tema gelap dan terang, di mana status tema disimpan di dalam ViewModel.
4.  **Responsive UI**: Tampilan profil yang informatif dengan kartu detail kontak (Email, Telepon, Lokasi).

## Struktur Folder

Sesuai dengan instruksi, proyek ini diorganisir ke dalam paket-paket berikut:

*   **`ui/`**: Berisi komponen UI Compose, termasuk `ProfileScreen.kt` dan tema aplikasi.
*   **`viewmodel/`**: Berisi `ProfileViewModel.kt` yang mengelola logika bisnis dan state UI.
*   **`data/`**: Berisi data class `ProfileUiState.kt` yang mendefinisikan struktur data state profil.

## Screenshot Aplikasi

Berikut adalah tampilan aplikasi dalam berbagai kondisi:

### 1. Profile View
<img width="720" height="1600" alt="WhatsApp Image 2026-05-03 at 21 52 58" src="https://github.com/user-attachments/assets/c5cb030e-9d2d-4679-b19a-62b5012ee221" />

*Tampilan utama profil pengguna.*

### 2. Edit Form
<img width="720" height="1600" alt="WhatsApp Image 2026-05-03 at 21 52 57 (1)" src="https://github.com/user-attachments/assets/d5faf033-2752-4942-9860-85f6a8434f33" />

*Formulir untuk mengubah nama dan bio.*

### 3. Dark Mode
<img width="720" height="1600" alt="WhatsApp Image 2026-05-03 at 21 52 57" src="https://github.com/user-attachments/assets/0479f69b-232b-44c4-bd77-923ff5786df0" />

*Tampilan aplikasi saat Dark Mode diaktifkan.*
