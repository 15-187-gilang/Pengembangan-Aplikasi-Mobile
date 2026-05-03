# News Feed Simulator - Kotlin

Proyek ini adalah aplikasi simulasi feed berita yang mendemonstrasikan penggunaan fitur-fitur modern Kotlin seperti **Flow**, **Coroutines**, dan **StateFlow**.

## Fitur Utama

1.  **Simulasi Data Real-time**: Menggunakan Kotlin `Flow` untuk mengirimkan data berita baru secara otomatis setiap 2 detik.
2.  **Filter Kategori**: Menggunakan operator `.filter` untuk hanya menampilkan berita dari kategori tertentu (misal: "Tech").
3.  **Transformasi Data**: Menggunakan operator `.map` untuk mengubah data objek `News` menjadi format teks yang siap ditampilkan di UI.
4.  **State Management (StateFlow)**: Menggunakan `StateFlow` untuk menyimpan dan mengamati jumlah total berita yang telah diproses/dibaca secara reaktif.
5.  **Asynchronous Detail Fetching**: Menggunakan `async` coroutine untuk mensimulasikan pengambilan detail berita tambahan (seperti konten lengkap) secara asinkron tanpa memblokir thread utama.

## Struktur Proyek

-   `NewsFeedSimulator.kt`: Berisi logika bisnis utama, model data `News`, dan simulator flow. Dilengkapi dengan fungsi `main()` untuk pengujian cepat di terminal.
-   `MainActivity.kt`: Implementasi UI menggunakan **Jetpack Compose** untuk menampilkan feed berita secara visual pada perangkat Android.

## Cara Menjalankan

### 1. Menjalankan di Terminal (Standalone)
Jika Anda ingin melihat output logika di console tanpa menjalankan emulator:
1. Buka file `app/src/main/java/com/example/tugas2/NewsFeedSimulator.kt`.
2. Klik tombol **Run** (ikon segitiga hijau) di samping baris `fun main()`.
3. Hasil simulasi akan muncul di jendela **Run** Android Studio.

### 2. Menjalankan di Android Emulator/Device
1. Klik tombol **Run 'app'** di toolbar atas Android Studio.
2. Pilih emulator atau perangkat fisik yang terhubung.
3. Aplikasi akan menampilkan feed berita kategori "Tech" yang diperbarui setiap 2 detik.

## Teknologi yang Digunakan
- Kotlin Coroutines & Flow
- StateFlow & SharedFlow
- Jetpack Compose (untuk UI)
- Material Design 3
