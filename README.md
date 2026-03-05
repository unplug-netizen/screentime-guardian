# ScreenTime Guardian

Eine native Screen-Time-App für iOS (Swift/SwiftUI) und Android (Kotlin/Jetpack Compose) mit strikter System-API-Nutzung und Glassmorphism-Design.

## Architektur

```
screentime-guardian/
├── ios/                    # Swift/SwiftUI App
│   ├── ScreenTimeGuardian/
│   │   ├── Domain/        # Repository-Pattern, Models
│   │   ├── Blocking/      # DeviceActivity, ShieldConfiguration
│   │   ├── Gamification/  # Badge-Engine, Streaks
│   │   ├── Social/        # Challenges, Leaderboard
│   │   ├── Presentation/  # SwiftUI Views, Design-System
│   │   └── Subscription/  # RevenueCat Integration
│   └── ScreenTimeGuardian.xcodeproj
├── android/               # Kotlin/Jetpack Compose App
│   ├── app/src/main/java/com/screentime/guardian/
│   │   ├── domain/        # Repository-Pattern, Models
│   │   ├── blocking/      # UsageStatsManager, AccessibilityService
│   │   ├── gamification/  # Badge-Engine, Streaks
│   │   ├── social/        # Challenges, Leaderboard
│   │   ├── presentation/  # Compose UI, Design-System
│   │   └── subscription/  # RevenueCat Integration
│   └── build.gradle.kts
├── shared-docs/           # API-Verträge, Design-Specs
└── design-system/         # Glassmorphism Tokens, Animation-Curves
```

## System-Constraints

- **DATENQUELLEN**: Android UsageStatsManager, iOS ScreenTime API, System Clock, Biometrie
- **KEINE**: HealthKit, GPS, Kontakte, Kalender, Schrittzähler, externe APIs
- **NOTFALL-ENTSPERRUNG**: Biometrie/PIN-Check, <3 Sekunden
- **UI**: Glassmorphism, Serif-Überschriften (Playfair Display/New York), Mono-UI-Text

## Agent-Struktur

1. **Agent 1**: System-Architecture & API-Abstraction
2. **Agent 2**: Native Blocking & Focus Engine
3. **Agent 3**: Gamification & Statistics
4. **Agent 4**: Social & Competition Layer
5. **Agent 5**: Modern UI/UX Implementation
6. **Agent 6**: Subscription & Feature-Gating

## Git Workflow

```
main         → Production releases
agent/[name] → Agent-spezifische Features
feature/*    → Sub-features
```

## Setup

### iOS
```bash
cd ios
xcodegen generate
open ScreenTimeGuardian.xcodeproj
```

### Android
```bash
cd android
./gradlew assembleDebug
```

## Lizenz

Proprietary - FocusFlow Dev Team
