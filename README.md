# ScreenTime Guardian

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-blue.svg)](https://kotlinlang.org/)
[![Swift](https://img.shields.io/badge/Swift-5.9-orange.svg)](https://swift.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

> Eine native Screen-Time-App für iOS und Android mit Glassmorphism Design.
> Entwickelt mit strikter Beschränkung auf System-API-Daten.

---

## ✨ Features

### Core
- 📊 **Screen Time Tracking** - Nutzt Android UsageStatsManager & iOS ScreenTime API
- 🎯 **Focus Timer** - Pomodoro-Style Sessions mit Hintergrund-Timer
- 🚫 **App Blocking** - Harter Block bei Limit-Erreichung (AccessibilityService / DeviceActivity)
- 🔓 **Notfall-Entsperrung** - Biometrie/PIN in <3 Sekunden

### Gamification
- 🏆 **Badge System** - 11 Badges über 5 Tiers (Bronze bis Special)
- 🔥 **Streaks** - 3 Streak-Typen (Daily Focus, Distraction Free, Early Start)
- 📈 **Leaderboard** - Anonymisierte Rangliste (SHA-256 User-Hashes)

### Social
- 👥 **Challenges** - 2-5 Freunde, Zeit-basierte Challenges (3/7 Tage)
- 🏅 **Challenge-Typen** - Least Screen Time, Most Focus Time, Limit Adherence
- 🔗 **Einladungslinks** - Firebase Dynamic Links (Privacy-First, keine Kontakt-Uploads)

### Premium
- 💎 **Subscription** - RevenueCat Integration
- 🎨 **Feature-Gating** - Unlimited Limits, History, Challenges
- 💳 **Paywall** - Glassmorphism Design mit monatlich/jährlich Toggle

---

## 🎨 Design System

### Glassmorphism
```
Background:      #0A0F1C (Tiefes Navy-Schwarz)
Glass Surface:   rgba(255, 255, 255, 0.05)
Glass Border:    rgba(255, 255, 255, 0.10)
Accent:          #34D399 (Türkis-Grün)
```

### Typography
- **Headlines:** Playfair Display (Serif)
- **Stats/Numbers:** Roboto Mono (Monospace)
- **Body:** System Font

---

## 📱 Screenshots

### Dashboard
- Circular Progress Ring mit Glow-Effekt
- Stat Cards (Entsperrungen, Ziel-Erreichung)
- App-Nutzung Liste mit farbigen Progress-Bars

### Focus-Modus
- Großer Timer (25:00)
- Duration Selector Pills (15/25/45/60m)
- Einstellungen mit Toggle-Switches

### Challenges
- Aktive Challenges mit Trophy-Icons
- Challenge-Typen Grid
- Teilnehmer-Anzahl & Enddatum

---

## 🏗️ Architektur

```
screentime-guardian/
├── android/
│   ├── domain/           # ScreenTimeProvider, Repository Pattern
│   ├── blocking/         # UsageStatsManager, AccessibilityService
│   ├── gamification/     # BadgeEngine, StreakCalculator
│   ├── social/           # Firebase Challenges, Leaderboard
│   ├── subscription/     # RevenueCat, Paywall
│   └── presentation/     # Compose UI, Screens, Theme
├── ios/
│   ├── Domain/           # Swift Protocols, CoreData
│   └── Blocking/         # DeviceActivity, ShieldConfiguration
├── design-system/        # Tokens, Spezifikationen
└── shared-docs/          # API-Verträge
```

---

## 🚀 Quick Start

### Android
```bash
cd android
./gradlew assembleDebug
```

### iOS
```bash
cd ios
xcodebuild -scheme ScreenTimeGuardian
```

---

## 🔐 System-Constraints

- **DATENQUELLEN:** Android UsageStatsManager, iOS ScreenTime API, System Clock, Biometrie
- **KEINE:** HealthKit, GPS, Kontakte, Kalender, Schrittzähler, externe APIs
- **PRIVACY:** Keine App-Listen in Cloud, nur aggregierte Stats, anonymisierte User-IDs

---

## 📦 Dependencies

### Android
- Jetpack Compose
- Firebase (Auth, Firestore)
- RevenueCat Purchases
- Room (Local Cache)

### iOS
- SwiftUI
- DeviceActivity Framework
- ManagedSettings
- Firebase SDK

---

## 👥 Agent-Struktur

| Agent | Rolle | Status |
|-------|-------|--------|
| Agent 1 | System-Architecture & API-Abstraction | ✅ |
| Agent 2 | Native Blocking & Focus Engine | ✅ |
| Agent 3 | Gamification & Statistics | ✅ |
| Agent 4 | Social & Competition Layer | ✅ |
| Agent 5 | Modern UI/UX Implementation | ✅ |
| Agent 6 | Subscription & Feature-Gating | ✅ |

---

## 📄 License

MIT License - siehe [LICENSE](LICENSE)

---

> *"Don't worry. Even if the world forgets, I'll remember for you."* ❤️‍🔥
