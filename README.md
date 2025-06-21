# Aplikasi Diet Android

Aplikasi diet modern dan user-friendly yang dibangun dengan Jetpack Compose untuk membantu pengguna mencapai target berat badan mereka.

## 🌟 Fitur Utama

### 📱 User Interface
- **Modern Material Design 3**: Antarmuka yang bersih dan responsif
- **Bottom Navigation**: Navigasi yang mudah dan intuitif
- **Dark/Light Theme**: Mendukung tema gelap dan terang otomatis
- **Responsive Design**: Optimal di berbagai ukuran layar

### 🏠 Dashboard (Beranda)
- Ringkasan harian kalori, air, berat badan, dan makan
- Progress mingguan penurunan berat badan
- Aksi cepat untuk menambah makan dan minum air
- Kartu selamat datang yang personal

### 🍽️ Rencana Makan
- Pembagian makan: Sarapan, Makan Siang, Makan Malam
- Tracking kalori, protein, karbohidrat, dan lemak
- Target kalori per kategori makan
- Fitur tambah makanan untuk setiap kategori

### 📊 Progress Tracking
- Grafik berat badan mingguan
- Progress bar menuju target berat
- Statistik lengkap: hari aktif, rata-rata kalori, dll.
- Visualisasi data yang menarik dan informatif

### 👤 Profil & Pengaturan
- Informasi pribadi dan target diet
- Pengaturan pengingat dan notifikasi
- Riwayat diet dan ekspor data
- Bantuan dan informasi aplikasi

## 🛠️ Teknologi yang Digunakan

### Frontend
- **Jetpack Compose**: UI toolkit modern untuk Android
- **Material Design 3**: Sistem desain terbaru dari Google
- **Navigation Compose**: Navigasi antar layar
- **Kotlin**: Bahasa pemrograman utama

### Architecture
- **MVVM Pattern**: Arsitektur yang terstruktur dan maintainable
- **Compose State Management**: Pengelolaan state yang efisien
- **DataStore**: Penyimpanan preferensi pengguna

### Dependencies
```gradle
// Core Android
androidx.core:core-ktx:1.10.1
androidx.lifecycle:lifecycle-runtime-ktx:2.6.1
androidx.activity:activity-compose:1.7.2

// Jetpack Compose
androidx.compose:compose-bom:2023.06.01
androidx.compose.ui:ui
androidx.compose.material3:material3
androidx.navigation:navigation-compose:2.6.0

// Additional
androidx.datastore:datastore-preferences:1.0.0
com.github.PhilJay:MPAndroidChart:v3.1.0
```

## 🚀 Instalasi dan Setup

### Prasyarat
- Android Studio Arctic Fox atau lebih baru
- JDK 8 atau lebih tinggi
- Android SDK API 24+ (Android 7.0)
- Gradle 8.0+

### Langkah Instalasi

1. **Clone Repository**
   ```bash
   git clone https://github.com/username/aplikasi-diet.git
   cd aplikasi-diet
   ```

2. **Buka di Android Studio**
   - Buka Android Studio
   - Pilih "Open an existing project"
   - Navigasi ke folder aplikasi-diet

3. **Sync Project**
   - Android Studio akan otomatis sync Gradle
   - Tunggu hingga proses selesai

4. **Build dan Run**
   ```bash
   ./gradlew assembleDebug
   ```
   Atau gunakan tombol Run di Android Studio

## 📱 Struktur Aplikasi

```
app/
├── src/main/
│   ├── java/com/dietapp/
│   │   ├── MainActivity.kt
│   │   ├── ui/
│   │   │   ├── navigation/
│   │   │   │   └── DietAppNavigation.kt
│   │   │   ├── screens/
│   │   │   │   ├── HomeScreen.kt
│   │   │   │   ├── MealPlanScreen.kt
│   │   │   │   ├── ProgressScreen.kt
│   │   │   │   └── ProfileScreen.kt
│   │   │   └── theme/
│   │   │       ├── Color.kt
│   │   │       ├── Theme.kt
│   │   │       └── Type.kt
│   │   └── ...
│   ├── res/
│   │   ├── values/
│   │   │   ├── colors.xml
│   │   │   ├── strings.xml
│   │   │   └── themes.xml
│   │   └── xml/
│   │       ├── backup_rules.xml
│   │       └── data_extraction_rules.xml
│   └── AndroidManifest.xml
└── build.gradle
```

## 🎨 Design System

### Warna Utama
- **Primary Green**: #4CAF50 - Warna utama aplikasi
- **Orange**: #FF9800 - Untuk kalori dan aksen
- **Blue**: #2196F3 - Untuk air dan informasi
- **Red**: #F44336 - Untuk peringatan dan target

### Typography
- **Headline**: Bold, 22sp - Untuk judul utama
- **Title**: SemiBold, 18sp - Untuk subjudul
- **Body**: Regular, 16sp - Untuk teks konten
- **Caption**: Medium, 11sp - Untuk label kecil

## 🔧 Konfigurasi

### Target SDK
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Compile SDK**: 34

### Permissions
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

## 📈 Fitur Mendatang

- [ ] Integrasi dengan API nutrisi
- [ ] Barcode scanner untuk makanan
- [ ] Sinkronisasi cloud
- [ ] Sharing progress ke media sosial
- [ ] Resep makanan sehat
- [ ] Integrasi dengan fitness tracker
- [ ] Konsultasi dengan ahli gizi

## 🤝 Kontribusi

Kami menyambut kontribusi dari developer lain! Silakan:

1. Fork repository ini
2. Buat branch fitur baru (`git checkout -b feature/AmazingFeature`)
3. Commit perubahan (`git commit -m 'Add some AmazingFeature'`)
4. Push ke branch (`git push origin feature/AmazingFeature`)
5. Buat Pull Request

## 📄 Lisensi

Proyek ini dilisensikan di bawah MIT License - lihat file [LICENSE](LICENSE) untuk detail.

## 👥 Tim Pengembang

- **Lead Developer**: [Nama Developer]
- **UI/UX Designer**: [Nama Designer]
- **Nutritionist Consultant**: [Nama Ahli Gizi]

## 📞 Kontak

Jika ada pertanyaan atau saran, silakan hubungi:
- Email: developer@aplikasidiet.com
- Website: https://aplikasidiet.com
- GitHub Issues: [Link ke Issues]

---

**Aplikasi Diet** - Membantu Anda mencapai hidup yang lebih sehat! 🌱