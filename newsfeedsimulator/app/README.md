# News Feed Simulator

Proyek ini adalah simulasi News Feed sederhana yang dibangun menggunakan **Kotlin Coroutines** dan **Flow**. Aplikasi ini mensimulasikan aliran data berita secara real-time dengan berbagai fitur pemrosesan data.

## Fitur Utama

1.  **Simulasi Aliran Data (Flow):** Menghasilkan data berita baru secara otomatis setiap 2 detik.
2.  **Penyaringan Berita (Filter):** Memungkinkan penyaringan berita berdasarkan kategori tertentu (misalnya: Tech, Health, dll).
3.  **Transformasi Data (Map):** Mengubah data mentah menjadi format model tampilan (Display Model) sebelum ditampilkan.
4.  **Pelacakan State (StateFlow):** Menyimpan dan memantau jumlah berita yang telah "dibaca" secara reaktif.
5.  **Pengambilan Detail Async:** Menggunakan Coroutines untuk mensimulasikan pengambilan detail berita secara asynchronous tanpa memblokir thread utama.

## Struktur Project

- `NewsFeedSimulator.kt`: Berisi logika utama simulator, termasuk Data Class, NewsFeedManager, dan fungsi `main` untuk menjalankan simulasi.
- `MainActivity.kt`: Entry point standar aplikasi Android.

## Cara Menjalankan

### Melalui Android Studio (Kotlin Main Function)
1. Buka file `app/src/main/java/com/example/tugas2/NewsFeedSimulator.kt`.
2. Cari fungsi `fun main()`.
3. Klik ikon "Run" (segitiga hijau) di sebelah kiri baris `fun main()`.
4. Lihat output simulasi pada jendela **Run** di bagian bawah Android Studio.

### Konfigurasi Filter
Anda dapat mengubah kategori filter pada baris berikut di dalam `fun main()`:
```kotlin
val filterCategory = "Tech" // Ganti dengan "Health", "Politics", atau "Sports"
```

## Pengembang
- **Nama:** [Nama Anda]
- **NIM:** [NIM Anda]
