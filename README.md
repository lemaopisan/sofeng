# SplitEase

SplitEase is a modern Android application designed to simplify splitting bills and receipts among friends and family. Using advanced camera integration and a seamless user experience, SplitEase makes the process of sharing costs transparent and hassle-free.

## 🚀 Features

- **Receipt Scanning**: Capture receipts using the integrated camera (powered by CameraX).
- **Item Selection**: Easily select and assign items to different participants.
- **Deep Linking**: Share sessions via custom URLs (`https://splitease.app/join/{roomId}`) for quick joining.
- **Modern UI**: Built entirely with Jetpack Compose for a fluid and responsive experience.
- **Session Management**: Create and manage "Split Rooms" for group payments.

## 🛠 Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Navigation**: Compose Navigation
- **Camera**: CameraX
- **Image Loading**: Coil
- **Architecture**: Modern Android Development (MAD) practices

## 📂 Project Structure

- `app/src/main/java/com/example/splitease/ui/screens`: Contains all the UI screens (Home, Scan, Split, etc.)
- `app/src/main/java/com/example/splitease/navigation`: Defines the application flow and deep link handling.
- `app/src/main/java/com/example/splitease/ui/theme`: Project-wide styling and themes.
- `docs/`: Technical documentation and project reports.

## 🏁 Getting Started

1. Clone the repository.
2. Open the project in **Android Studio (Hedgehog or newer)**.
3. Build and run on an emulator or physical device (API 24+).

---
*Developed as part of the Software Engineering course.*
