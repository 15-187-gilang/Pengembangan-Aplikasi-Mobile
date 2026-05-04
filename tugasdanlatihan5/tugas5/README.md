# Notes App - Tugas PAM Minggu 5

Aplikasi Catatan sederhana yang dibangun menggunakan Jetpack Compose dengan fitur navigasi lengkap.

## Fitur
1. **Bottom Navigation**: 3 Tab utama (Notes, Favorites, Profile).
2. **Note List to Detail**: Navigasi ke detail catatan dengan pengiriman ID.
3. **Add Note**: Navigasi menggunakan Floating Action Button (FAB).
4. **Edit Note**: Fitur edit catatan dengan passing argument `noteId`.
5. **Back Navigation**: Implementasi navigasi balik yang proper di semua layar.
6. **Favorites**: Menyaring catatan yang ditandai sebagai favorit.

## Struktur Folder
Sesuai instruksi, proyek ini diorganisir sebagai berikut:
- `navigation/`: Berisi definisi rute dan `Screen` class.
- `screens/`: Berisi semua layar utama aplikasi (Notes, Detail, Add/Edit, Profile).
- `components/`: Berisi komponen UI yang dapat digunakan kembali (seperti `NoteItem`).
- `data/`: Model data dan state UI.
- `viewmodel/`: Logic aplikasi dan state management.

## Diagram Alur Navigasi (Navigation Flow)

```mermaid
graph TD
    Start((Start)) --> Notes[Notes Screen]
    
    subgraph Bottom Navigation
        Notes
        Favorites[Favorites Screen]
        Profile[Profile Screen]
    end
    
    Notes -->|Click Note| Detail[Note Detail Screen]
    Notes -->|Click FAB| Add[Add Note Screen]
    Notes -->|Click Edit| Edit[Edit Note Screen]
    
    Favorites -->|Click Note| Detail
    
    Detail -->|Back| Notes
    Add -->|Save/Back| Notes
    Edit -->|Save/Back| Notes
    
    Notes <--> Favorites
    Favorites <--> Profile
    Profile <--> Notes
```

## Screenshots

| Notes Screen | Favorites Screen | Profile Screen |
|--------------|------------------|----------------|
| <img width="1080" height="2400" alt="WhatsApp Image 2026-05-03 at 15 25 07 (3)" src="https://github.com/user-attachments/assets/52fe2c23-5f41-45d2-b763-ce1bfd2cd2cd" /> | <img width="1080" height="2400" alt="WhatsApp Image 2026-05-03 at 15 25 07 (4)" src="https://github.com/user-attachments/assets/d2c3b80b-02db-42e7-be8e-c8cb9288b5ce" /> | <img width="720" height="1600" alt="WhatsApp Image 2026-05-03 at 15 25 08 (4)" src="https://github.com/user-attachments/assets/de0cbd6f-ea6f-4242-974f-23211b6fa732" /> |

| Add Screen | Note Detail | 
|------------|-------------|
| <img width="1080" height="2400" alt="WhatsApp Image 2026-05-03 at 15 25 08 (5)" src="https://github.com/user-attachments/assets/524c24d3-900d-4c81-94f6-4ae5ec4e19cf" /> | <img width="1080" height="2400" alt="WhatsApp Image 2026-05-03 at 15 32 07 (2)" src="https://github.com/user-attachments/assets/d8db107c-ae1d-4cec-b04d-bf90da4cca69" /> |

## Video Demo
https://github.com/user-attachments/assets/46912a0f-2293-4b0d-8c17-8a27e0a08c93


---
**Mahasiswa**: Gilang Surya Agung
**NIM**: 123140187
