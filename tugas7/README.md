# Notes App - Tugas 7

Aplikasi catatan fungsional yang dibangun dengan praktik pengembangan Android modern, mendukung penyimpanan lokal, manajemen pengaturan, dan penanganan status UI yang tepat.

## Features
- **Offline-first Architecture**: Data disimpan secara lokal menggunakan **SQLDelight**, memastikan aplikasi tetap berfungsi tanpa koneksi internet.
- **CRUD Operations**: Mendukung pembuatan (Create), pembacaan (Read), pembaruan (Update), dan penghapusan (Delete) catatan.
- **Search Functionality**: Pencarian real-time pada judul dan isi catatan.
- **Settings (DataStore)**: Preferensi pengguna yang persisten untuk **Mode Gelap (Dark Mode)** dan **Urutan Pengurutan** (Terbaru, Terlama, Abjad) menggunakan **Jetpack DataStore**.
- **UI States**: Penanganan status *Loading*, *Empty* (Kosong), dan *Content* (Konten) untuk pengalaman pengguna yang lebih baik.
- **Favorites**: Kemampuan untuk menandai catatan sebagai favorit untuk akses cepat.
- **Profile**: Layar profil khusus untuk mengelola informasi pengguna.

## Database Schema
 Aplikasi ini menggunakan SQLDelight untuk penyimpanan lokal terstruktur. Berikut adalah skema untuk tabel `noteEntity`:
 
```sql
CREATE TABLE noteEntity (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    isFavorite INTEGER NOT NULL DEFAULT 0,
    createdAt INTEGER NOT NULL
);
```

## Screenshots
| Notes List | Edit Notes | Add Note |
|:---:|:---:|:---:|
| <img width="1080" height="2400" alt="WhatsApp Image 2026-05-04 at 15 16 25" src="https://github.com/user-attachments/assets/bcdd2c97-88ca-4525-88c2-12f4635670ac" /> | <img width="1080" height="2400" alt="WhatsApp Image 2026-05-04 at 15 16 26" src="https://github.com/user-attachments/assets/d39e48d9-17e8-4f70-ad0f-8ef76b207ef1" /> | <img width="1080" height="2400" alt="WhatsApp Image 2026-05-04 at 15 16 28" src="https://github.com/user-attachments/assets/4350540f-4f1f-4fa8-b6cc-4aa7884d6b8d" /> |

| Note Detail | Profile Screen | Settings Screen |
|:---:|:---:|:---:|
| <img width="1080" height="2400" alt="WhatsApp Image 2026-05-04 at 16 37 45" src="https://github.com/user-attachments/assets/d0ac58f7-9776-47e4-8c18-408766e81547" /> | <img width="1080" height="2400" alt="WhatsApp Image 2026-05-04 at 15 16 27" src="https://github.com/user-attachments/assets/9b4d23af-d1ae-414c-9e50-085e5a05fb86" /> | <img width="1080" height="2400" alt="WhatsApp Image 2026-05-04 at 15 16 27 (1)" src="https://github.com/user-attachments/assets/d2401dcd-4a67-47f1-a208-42b4357e0def" /> |


## Video Demo
Video demo berdurasi 45 detik menunjukkan fungsionalitas inti aplikasi:
1. **CRUD**: Membuat catatan, melihat detail, mengedit, dan menghapus.
2. **Pencarian**: Menemukan catatan secara efisien menggunakan bilah pencarian.
3. **Pengaturan**: Mengubah tema aplikasi ke Mode Gelap dan mengatur ulang urutan catatan.
4. **Mode Offline**: Menunjukkan bahwa data tetap utuh saat aplikasi dibuka kembali tanpa ketergantungan jaringan.

https://github.com/user-attachments/assets/dfacde7f-2987-410b-9304-cbac2c568fd9

---
