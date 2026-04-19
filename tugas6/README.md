# News Reader App - Kotlin Multiplatform

Aplikasi News Reader sederhana yang dibangun menggunakan **Compose Multiplatform (KMP)**. Aplikasi ini mengambil data dari API publik dan menampilkannya dalam daftar artikel dengan fitur detail, penyegaran data (*pull-to-refresh*), dan penanganan status UI yang lengkap.

## 📸 Fitur Utama
Sesuai dengan deskripsi tugas, aplikasi ini mencakup:
1. **Fetch Berita dari Public API**: Mengambil data artikel dari [JSONPlaceholder](https://jsonplaceholder.typicode.com/posts).
2. **List Artikel**: Menampilkan judul, deskripsi, dan gambar dinamis menggunakan placeholder.
3. **Detail Screen**: Layar khusus yang menampilkan isi lengkap artikel saat item daftar diklik.
4. **Pull to Refresh**: Kemampuan untuk memperbarui daftar berita dengan menarik layar ke bawah.
5. **UI States**: Implementasi state **Loading** (indikator putar), **Success** (tampilan data), dan **Error** (pesan kesalahan dengan tombol coba lagi).
6. **Repository Pattern**: Pemisahan logika data menggunakan pola Repository untuk pengelolaan API yang bersih.

## 🛠️ Tech Stack
- **Kotlin Multiplatform**: Berbagi logika bisnis dan UI antara Android dan iOS.
- **Compose Multiplatform**: Framework UI deklaratif untuk berbagai platform.
- **Ktor Client**: Digunakan untuk melakukan HTTP request ke API.
- **Coil 3**: Library pemuatan gambar yang dioptimalkan untuk KMP.
- **Kotlinx Serialization**: Untuk parsing data JSON.
- **Navigation Compose**: Navigasi antar layar yang mulus.
- **Material 3**: Komponen desain modern dari Google.

## 🏗️ Arsitektur
Aplikasi ini mengikuti pola arsitektur **MVVM (Model-View-ViewModel)** dengan **Repository Pattern**:
- **Data Layer**: Terdiri dari `NewsApi` (Ktor) dan `NewsRepository`.
- **Domain Layer**: Model data `Article`.
- **UI Layer**: `NewsViewModel` mengelola state, sementara `NewsListScreen` dan `ArticleDetailScreen` menangani tampilan.

## 🚀 Cara Menjalankan
### Android
1. Buka proyek ini di Android Studio.
2. Pilih konfigurasi `composeApp` di widget run.
3. Jalankan pada emulator atau perangkat fisik Android.

Atau via terminal:
```shell
.\gradlew.bat :composeApp:assembleDebug
```

### iOS (Memerlukan macOS & Xcode)
1. Buka direktori `/iosApp` di Xcode.
2. Jalankan aplikasi pada simulator iPhone.

---
*Tugas ini dikerjakan untuk memenuhi kriteria aplikasi News Reader yang sederhana namun fungsional dan menarik.*
