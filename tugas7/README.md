# Notes App - Tugas 7 PAM

A fully functional Notes application built with modern Android development practices, featuring local persistence, settings management, and proper UI state handling.

## Features
- **Offline-first Architecture**: Data is stored locally using **SQLDelight**, ensuring the app works without internet.
- **CRUD Operations**: Full support for Creating, Reading, Updating, and Deleting notes.
- **Search Functionality**: Real-time search across note titles and content.
- **Settings (DataStore)**: Persistent user preferences for **Dark Mode** and **Sort Order** (Newest, Oldest, Alphabetical) using **Jetpack DataStore**.
- **UI States**: Robust handling of Loading, Empty, and Content states for a better user experience.
- **Favorites**: Ability to mark notes as favorites for quick access.
- **Profile**: A dedicated profile screen to manage user information.

## Database Schema
The application uses SQLDelight for structured local storage. Below is the schema for the `noteEntity` table:

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
| Notes List (Empty) | Notes List (Content) | Add Note |
|:---:|:---:|:---:|
| ![Empty List](docs/screenshots/notes_empty.png) | ![Notes Content](docs/screenshots/notes_list.png) | ![Add Note](docs/screenshots/add_note.png) |

| Note Detail | Profile Screen | Settings Screen |
|:---:|:---:|:---:|
| ![Note Detail](docs/screenshots/note_detail.png) | ![Profile](docs/screenshots/profile.png) | ![Settings](docs/screenshots/settings.png) |

*(Note: Please place the actual screenshots in the `docs/screenshots/` folder)*

## Video Demo
The video below (45 seconds) demonstrates the core functionality of the app:
1. **CRUD**: Creating a note, viewing details, editing, and deleting.
2. **Search**: Efficiently finding notes using the search bar.
3. **Settings**: Changing the app theme to Dark Mode and re-ordering notes.
4. **Offline Mode**: Showing that data remains intact across app restarts without network dependency.

[Watch Video Demo (Demo.mp4)](docs/demo/demo.mp4)

---
**Tugas 7 - Pemrograman Aplikasi Mobile (PAM)**  
*Teknik Informatika - Institut Teknologi Sumatera (ITERA)*
