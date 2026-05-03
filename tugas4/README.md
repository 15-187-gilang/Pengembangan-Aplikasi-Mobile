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
![Profile View](screenshots/profile_view.png)
*Tampilan utama profil pengguna.*

### 2. Edit Form
![Edit Form](screenshots/edit_form.png)
*Formulir untuk mengubah nama dan bio.*

### 3. Dark Mode
![Dark Mode](screenshots/dark_mode.png)
*Tampilan aplikasi saat Dark Mode diaktifkan.*

---
*Catatan: Ganti gambar di folder `screenshots/` dengan hasil screenshot asli Anda.*
