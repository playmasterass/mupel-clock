# 🕐 Mupel Clock - Premium Android App

A production-ready, feature-rich Android clock application built with **Kotlin**, **Jetpack Compose**, and **Material Design 3**. Includes a clock, timer, stopwatch, floating overlay widget, and more.

## 📱 Features

### 🕰️ Clock Module
- **Live Digital Clock** with 12/24-hour format toggle
- **Date & Day Display** with beautiful animations
- **AMOLED Dark Theme** with optional light theme
- Smooth transitions and premium UI

### ⏱️ Timer Module
- **Complete Controls**: Start, Pause, Resume, Stop, Reset, Restart
- **Quick Add Buttons**: +1 min, +5 min, +10 min
- **Foreground Service** with notification controls
- **Background Countdown** - continues after screen lock/app minimize
- **Horizontal Progress Indicator**
- **Pre-built Presets**: 5, 10, 15, 25, 30, 45, 60 minutes
- **Custom Presets** support

### ⏲️ Stopwatch Module
- **Precise Timing** with millisecond accuracy
- **Lap Times** tracking
- Start, Pause, Resume, Reset controls
- Real-time display updates

### 🎯 Floating Overlay Widget
- **WindowManager Floating Timer** - works above all apps
- **Draggable & Snappable** to screen edges
- **Compact & Expanded Modes**
- **Quick Controls**: Pause, Resume, Stop, +5 Minutes
- **Permission Handling** with user-friendly dialogs

### 🔔 Notifications
- **Foreground Service Notification** with action buttons
- **Premium Time's Up Screen** with alarm and haptic feedback
- Action buttons: Start Again, Add 5 Minutes, Stop

### ⚙️ Settings
- **Theme Modes**: Dark, Light, AMOLED
- **Alarm Sound Selection** with preview
- **Vibration Toggle**
- **12/24 Hour Format Preference**
- **Keep Screen Awake Option**
- **Timer Presets Management**

## 🏗️ Architecture

- **MVVM** with Clean Architecture
- **Dependency Injection** using Hilt
- **State Management** with StateFlow & Compose
- **Room Database** for data persistence
- **Kotlin Coroutines** for async operations
- **SOLID Principles** throughout

## 🛠️ Technology Stack

```
- Kotlin 1.9+
- Jetpack Compose
- Material Design 3
- Jetpack Hilt
- Room Database
- Navigation Compose
- Kotlin Coroutines & StateFlow
- Foreground Services
- WindowManager API
```

## 📋 Project Structure

```
mupel-clock/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/com/mupel/clock/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── app/
│   │   │   │   │   └── MupelClockApp.kt
│   │   │   │   ├── di/
│   │   │   │   │   ├── AppModule.kt
│   │   │   │   │   ├── DatabaseModule.kt
│   │   │   │   │   └── ServiceModule.kt
│   │   │   │   ├── ui/
│   │   │   │   │   ├── navigation/
│   │   │   │   │   ├── screens/
│   │   │   │   │   │   ├── ClockScreen.kt
│   │   │   │   │   │   ├── TimerScreen.kt
│   │   │   │   │   │   ├── StopwatchScreen.kt
│   │   │   │   │   │   └── SettingsScreen.kt
│   │   │   │   │   ├── components/
│   │   │   │   │   └── theme/
│   │   │   │   ├── viewmodel/
│   │   │   │   │   ├── ClockViewModel.kt
│   │   │   │   │   ├── TimerViewModel.kt
│   │   │   │   │   ├── StopwatchViewModel.kt
│   │   │   │   │   └── SettingsViewModel.kt
│   │   │   │   ├── data/
│   │   │   │   │   ├── local/
│   │   │   │   │   ├── model/
│   │   │   │   │   ├── repository/
│   │   │   │   │   └── prefs/
│   │   │   │   ├── service/
│   │   │   │   │   ├── TimerService.kt
│   │   │   │   │   └── OverlayService.kt
│   │   │   │   └── util/
│   │   │   ├── res/
│   │   │   │   ├── values/
│   │   │   │   ├── drawable/
│   │   │   │   └── raw/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
├── build.gradle.kts
├── settings.gradle.kts
├── .gitignore
├── LICENSE
├── CHANGELOG.md
└── README.md
```

## 🚀 Getting Started

### Prerequisites
- Android SDK 34+ (API level 34)
- Gradle 8.0+
- Kotlin 1.9+
- GitHub Codespaces or local Android development environment

### Setup Instructions

#### Option 1: GitHub Codespaces (Recommended for Phone Development)

1. **Open in Codespaces**
   ```bash
   # In GitHub, click "Code" → "Codespaces" → "Create codespace on main"
   ```

2. **Install Required Tools**
   ```bash
   # Android SDK (pre-installed in Codespaces)
   # No additional setup needed!
   ```

3. **Build the Project**
   ```bash
   ./gradlew build
   ```

4. **Generate APK**
   ```bash
   ./gradlew assembleDebug
   # Output: app/build/outputs/apk/debug/app-debug.apk
   ```

#### Option 2: Local Setup

1. **Clone Repository**
   ```bash
   git clone https://github.com/playmasterass/mupel-clock.git
   cd mupel-clock
   ```

2. **Build Project**
   ```bash
   ./gradlew build
   ```

3. **Run on Device/Emulator**
   ```bash
   ./gradlew installDebug
   ```

### Installation from APK

1. Download the generated APK from `app/build/outputs/apk/debug/app-debug.apk`
2. Transfer to your Android device
3. Enable "Unknown Sources" in Settings
4. Tap the APK file to install
5. Grant required permissions when prompted

## 📖 Building from Codespaces

### Step-by-Step Guide

1. **Initial Setup**
   ```bash
   # Clone and enter directory
   git clone https://github.com/playmasterass/mupel-clock.git
   cd mupel-clock
   
   # Make gradle wrapper executable
   chmod +x gradlew
   ```

2. **Build Debug APK**
   ```bash
   ./gradlew clean assembleDebug
   ```

3. **Locate APK**
   ```bash
   find . -name "*.apk" -type f
   # Should show: app/build/outputs/apk/debug/app-debug.apk
   ```

4. **Download to Phone**
   - Use GitHub Codespaces file explorer
   - Right-click APK → Download
   - Transfer to your Android device

5. **Install on Device**
   - Tap downloaded APK file
   - Grant permissions
   - Launch app

### Troubleshooting

**Build fails with SDK error:**
```bash
./gradlew --version  # Check installed SDK
./gradlew sdkmanager  # Update SDK if needed
```

**APK not generating:**
```bash
./gradlew clean
./gradlew assembleDebug --stacktrace
```

**Permissions denied:**
```bash
chmod +x gradlew
```

## 🎨 UI/UX Highlights

### Material Design 3
- Dynamic color system with purple Mupel branding
- Smooth animations and transitions
- Responsive layouts for all screen sizes
- Accessibility-first design

### Themes
- **Dark Mode**: Easy on the eyes, reduces battery drain
- **Light Mode**: Bright and clean
- **AMOLED Mode**: Pure black background for maximum battery efficiency

### Premium Feel
- Rounded corners throughout
- Beautiful typography hierarchy
- Smooth micro-interactions
- Fast performance (60 FPS animations)

## 🔧 Development

### Key Directories

- **app/src/main/kotlin/com/mupel/clock/** - Main source code
- **app/src/main/res/** - Resources (images, strings, colors)
- **gradle/libs.versions.toml** - Dependency versions
- **app/build.gradle.kts** - App-level build configuration

### Building & Running

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run tests
./gradlew test

# Check code quality
./gradlew detekt  # if enabled
```

### Adding Dependencies

Edit `gradle/libs.versions.toml`:
```toml
[versions]
# Add version here

[libraries]
# Add library reference here
```

Then use in `app/build.gradle.kts`:
```kotlin
dependencies {
    implementation(libs.your.library)
}
```

## 📱 Permissions Required

The app requests these Android permissions:

```xml
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
```

All permissions are requested at runtime with proper dialogs.

## 🧪 Testing

```bash
# Unit tests
./gradlew test

# Instrumented tests (on device)
./gradlew connectedAndroidTest

# Run specific test
./gradlew test --tests ClassName
```

## 🔐 Privacy & Security

- No external API calls
- No data collection
- Fully offline-capable
- Room database with encrypted SharedPreferences for settings
- Proper permission handling

## 📦 Release Build

```bash
# Generate release APK
./gradlew assembleRelease

# Generate release bundle (for Play Store)
./gradlew bundleRelease

# Output locations:
# - APK: app/build/outputs/apk/release/app-release.apk
# - Bundle: app/build/outputs/bundle/release/app-release.aab
```

## 🐛 Known Issues & Limitations

- Floating overlay requires Android 6.0+ (API 23)
- Some animations may vary on older devices
- Stopwatch precision is millisecond-level (not nanosecond)

## 📄 License

MIT License - See LICENSE file for details

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## 📞 Support

For issues, questions, or suggestions:
- Open an issue on GitHub
- Check existing issues first
- Provide clear reproduction steps

## 🎯 Roadmap

- [ ] Widget support (home screen)
- [ ] Multiple alarm sounds
- [ ] Cloud sync
- [ ] Wear OS companion app
- [ ] Material You dynamic theming
- [ ] Custom color schemes

## 👨‍💻 About

**Mupel Clock** is a demonstration of production-ready Android development using modern tools and best practices. Perfect for learning, reference, or as a template for your own apps.

**Built with ❤️ using Kotlin & Jetpack Compose**

---

**Last Updated**: July 2026
**Version**: 1.0.0
