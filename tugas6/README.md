# News Reader App - Kotlin Jetpack Compose

Aplikasi pembaca berita sederhana yang dibangun menggunakan **Kotlin** dan **Jetpack Compose**. Aplikasi ini mengimplementasikan arsitektur **MVVM** dengan **Repository Pattern** untuk mengelola aliran data dari API.

## 🚀 Fitur Utama
- **Fetch Data Berita**: Mengambil berita terbaru dari Public API.
- **List & Detail Screen**: Menampilkan daftar artikel dan detail lengkap artikel saat diklik.
- **Pull-to-Refresh**: Memperbarui daftar berita dengan menarik layar ke bawah.
- **State Management**: Penanganan state **Loading**, **Success**, dan **Error** dengan UI yang informatif.
- **Image Loading**: Menggunakan library **Coil** untuk memuat gambar artikel secara asinkron.

## 🛠️ Stack Teknologi & Library
- **Bahasa**: Kotlin
- **UI**: Jetpack Compose
- **Networking**: Retrofit & OkHttp
- **JSON Serialization**: GSON
- **Architecture**: MVVM + Repository Pattern
- **Concurrency**: Coroutines & Flow (StateFlow)
- **Navigation**: Jetpack Navigation Compose
- **Image Loading**: Coil

## 📡 API yang Digunakan
Aplikasi ini menggunakan **Spaceflight News API v4** sebagai sumber data berita.
- **Base URL**: `https://api.spaceflightnewsapi.net/v4/`
- **Endpoint**: `/articles`

*(Catatan: Anda dapat menyesuaikan repository untuk menggunakan dummy data berita Indonesia sesuai kebutuhan tugas.)*

## 📸 Screenshots (States)

| Loading State | Success State (List) | Success State (Detail) | Error State |
| --- | --- | --- | --- |
| ![Loading](https://via.placeholder.com/200x400?text=Loading+State) | ![Success List](https://via.placeholder.com/200x400?text=Success+List) | ![Success Detail](https://via.placeholder.com/200x400?text=Success+Detail) | ![Error](https://via.placeholder.com/200x400?text=Error+State) |

> *Silakan ganti gambar di atas dengan screenshot asli dari aplikasi Anda.*

## 🎬 Video Demo
Video demo berdurasi 30 detik yang menunjukkan fungsionalitas:
1. **Loading**: Saat aplikasi pertama kali dibuka.
2. **Success**: Menampilkan daftar berita Indonesia.
3. **Error**: (Simulasikan dengan mematikan internet).
4. **Refresh**: Menggunakan fitur Pull-to-Refresh.

[Link Video Demo atau Tampilkan di sini]

---
**Tugas 6 - Pengembangan Aplikasi Mobile (PAM)**
