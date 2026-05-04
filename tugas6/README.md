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

## 📸 Screenshots (States)

| Loading State | Success State (List) | Success State (Detail) |
| --- | --- | --- |
| <img width="1080" height="2400" alt="WhatsApp Image 2026-05-03 at 21 23 01" src="https://github.com/user-attachments/assets/8b39131d-c3cc-430c-94c3-d752d84d7e78" /> | <img width="720" height="1600" alt="WhatsApp Image 2026-05-03 at 21 19 13" src="https://github.com/user-attachments/assets/f320465f-6ff6-416c-9c14-a20b9af68d92" /> | <img width="720" height="1600" alt="WhatsApp Image 2026-05-03 at 21 19 14" src="https://github.com/user-attachments/assets/be39f442-05b4-4779-958b-023092c05bda" /> |

## 🎬 Video Demo
Video demo berdurasi 30 detik yang menunjukkan fungsionalitas:
1. **Loading**: Saat aplikasi pertama kali dibuka.
2. **Success**: Menampilkan daftar berita Indonesia.
3. **Error**: (Simulasikan dengan mematikan internet).
4. **Refresh**: Menggunakan fitur Pull-to-Refresh.

https://github.com/user-attachments/assets/37cffbb0-efdd-44d1-be56-6ab4a1eb4c2b

---
