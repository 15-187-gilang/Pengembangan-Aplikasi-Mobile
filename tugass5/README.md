# Notes App - Tugas Week 5

Aplikasi pencatat sederhana yang dibangun menggunakan **Jetpack Compose** dengan sistem navigasi yang lengkap (Bottom Navigation dan Stack Navigation).

## 🚀 Fitur Utama
- **Bottom Navigation**: 3 Tab utama (Notes, Favorites, Profile).
- **Note List**: Menampilkan daftar catatan yang bisa diklik.
- **Note Detail**: Menampilkan isi detail catatan berdasarkan ID yang dikirim melalui argumen navigasi.
- **Add Note**: Navigasi ke layar tambah catatan melalui Floating Action Button (FAB).
- **Edit Note**: Fitur navigasi ke layar edit dari halaman detail.
- **Proper Back Navigation**: Tombol kembali di setiap sub-layar.

## 📁 Struktur Folder
Proyek ini mengikuti struktur yang rapi sesuai instruksi tugas:
- `com.example.tugas3.navigation`: Berisi definisi rute (`Screen.kt`).
- `com.example.tugas3.components`: Komponen UI yang dapat digunakan kembali (`BottomNavigationBar.kt`).
- `com.example.tugas3.model`: Model data dan data dummy (`Note.kt`).
- `com.example.tugas3`: Tampilan utama (`MainActivity.kt`).

## 🗺️ Flow Navigasi
1. **Layar Utama (Notes)** -> Klik Item -> **Detail Note**.
2. **Layar Utama (Notes)** -> Klik FAB (+) -> **Add Note**.
3. **Detail Note** -> Klik Edit -> **Edit Note**.
4. **Bottom Bar** -> Pindah antar **Notes**, **Favorites**, dan **Profile**.

## 📸 Screenshots
*(Tambahkan screenshot aplikasi Anda di sini)*
- <img width="386" height="858" alt="image" src="https://github.com/user-attachments/assets/c367c43d-e40a-4033-bc0d-2ae70fbc87da" />
- <img width="385" height="854" alt="image" src="https://github.com/user-attachments/assets/adf7ce99-8469-4912-bf56-d3b9fa27d491" />


## 🎥 Video Demo
https://github.com/user-attachments/assets/1e3f1e7c-d16f-4067-8e1a-d491d721b272

---
