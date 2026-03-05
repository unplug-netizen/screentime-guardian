# Glassmorphism Design System

## Design Tokens

### Farben

#### Dark Mode (Primary)
```
Background Primary:    #0A0F1C        // Tiefes Navy-Schwarz
Background Secondary:  #12182B        // Etwas heller
Background Tertiary:   #1A2340        // Card-Background
Background Elevated:   #1E2A4A        // Für hervorgehobene Cards

Glass Surface:         rgba(255, 255, 255, 0.05)  // 5% opacity
Glass Surface Elevated:rgba(255, 255, 255, 0.08)  // 8% für aktive Elemente
Glass Border:          rgba(255, 255, 255, 0.10)   // 10% opacity
Glass Border Strong:   rgba(255, 255, 255, 0.15)   // 15% opacity
Glass Highlight:       rgba(255, 255, 255, 0.20)   // 20% opacity

Accent Primary:        #34D399        // Türkis/Grün (Emerald 400)
Accent Secondary:      #10B981        // Dunkleres Grün
Accent Glow:           rgba(52, 211, 153, 0.3)    // Glow-Effekt

Text Primary:          #FFFFFF
Text Secondary:        rgba(255, 255, 255, 0.70)
Text Tertiary:         rgba(255, 255, 255, 0.50)
Text Muted:            rgba(255, 255, 255, 0.35)

// App-Kategorie Farben
App Social:            #EC4899        // Pink (Instagram)
App Messaging:         #10B981        // Grün (WhatsApp)
App Video:             #EF4444        // Rot (YouTube)
App Productivity:      #3B82F6        // Blau
App Other:             #6B7280        // Grau

Success:               #34D399
Warning:               #FBBF24
Error:                 #EF4444
```

#### Light Mode
```
Background Primary:    #FAFAFA
Background Secondary:  #F3F4F6
Background Tertiary:   #E5E7EB

Glass Surface:         rgba(255, 255, 255, 0.70)
Glass Border:          rgba(0, 0, 0, 0.08)
Glass Highlight:       rgba(255, 255, 255, 0.90)

Text Primary:          #0A0F1C
Text Secondary:        rgba(10, 15, 28, 0.70)
Text Tertiary:         rgba(10, 15, 28, 0.50)
```

### Typografie

#### Schriftarten
```
Headlines (H1-H3):     Playfair Display (iOS: New York Serif)
Stats/Numbers:         Roboto Mono (iOS: SF Mono)
Body/UI:               System Font (San Francisco / Roboto)
```

#### Größen
```
H1:     40px / 48px line-height / -0.5px letter-spacing  (wie "Challenges")
H2:     32px / 40px line-height / -0.3px letter-spacing  (wie "3h 30m")
H3:     20px / 28px line-height / -0.2px letter-spacing

Stats:  56px / 64px line-height / -1px letter-spacing (Timer "25:00")
Timer:  64px / 72px line-height / -2px letter-spacing (tabulare Zahlen)
Body:   16px / 24px line-height / 0px letter-spacing
Caption:12px / 16px line-height / 0.2px letter-spacing
```

### Animationen

#### Spring-Curve (Primary)
```swift
// iOS
.spring(response: 0.4, dampingFraction: 0.7)

// Android
SpringSpec(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessMedium
)
```

#### Durations
```
Micro (button press):  100ms
Short (state change):  200ms
Medium (transitions):  400ms
Long (page transitions): 600ms
```

#### Micro-Interactions
```
Button Press:   scale(0.96), 100ms spring
Card Hover:     translateY(-2px), 200ms spring
Progress Ring:  sweep gradient animation, 1.5s spring
Pill Selection: width/color morph, 200ms spring
Toggle Switch:  thumb slide, 150ms ease-out
```

### Glassmorphism-Komponenten

#### Glass Card
```
Background:      rgba(255, 255, 255, 0.05)
Backdrop-Blur:   20px
Border:          1px solid rgba(255, 255, 255, 0.10)
Border-Radius:   16px
Shadow:          0 8px 32px rgba(0, 0, 0, 0.12)
Padding:         16-20px
```

#### Primary Button (Filled)
```
Background:      #34D399 (durchgehend)
Border-Radius:   28dp (pill shape)
Height:          56dp
Text:            #0A0F1C (dunkel)
Font:            Bold, 16px
Icon:            Play/Plus (weiß oder dunkel je nach Kontrast)
Press-State:     scale(0.96), brightness 1.1
```

#### Duration Pill
```
Unselected:
  Background:    rgba(255, 255, 255, 0.05)
  Border:        1px rgba(255, 255, 255, 0.10)
  Text:          rgba(255, 255, 255, 0.70)
  
Selected:
  Background:    #34D399
  Border:        none
  Text:          #0A0F1C (dunkel)
  Font:          Bold
```

#### Toggle Switch
```
Track Off:       rgba(255, 255, 255, 0.15), 52x32dp
Track On:        #34D399, 52x32dp
Thumb:           #FFFFFF, 28dp circle
Animation:       slide 150ms ease-out
```

#### Stat Card
```
Background:      rgba(255, 255, 255, 0.05)
Border:          1px rgba(255, 255, 255, 0.10)
Border-Radius:   16dp
Icon-Container:  48dp circle, rgba(255,255,255,0.08)
Icon-Color:      #34D399 oder kategoriespezifisch
Layout:          Horizontal (Icon | Text)
```

#### App Usage Row
```
Icon:            44dp circle, farbiges Background (15% opacity)
Initial:         Bold, farbig
Progress-Bar:    6dp height, rounded
  Background:    rgba(255,255,255,0.08)
  Fill:          kategoriespezifische Farbe
Time:            Roboto Mono, rechts ausgerichtet
```

## Screen-Spezifikationen

### Dashboard
- Circular Progress Ring (280dp Durchmesser)
  - Stroke: 18dp
  - Gradient: Türkis sweep gradient
  - Glow: animierter äußerer Schimmer
  - Center: "3h 30m" / "von 4h 0m" / "30m verbleibend"
- Stat Cards: 2-Spalten Grid
  - "47 Entsperrungen" mit Lock-Icon
  - "88% Ziel erreicht" mit Target-Icon
- App-Nutzung Section
  - Header: "App-Nutzung" (links) + "Details >" (rechts)
  - Liste: Instagram (pink), WhatsApp (grün), YouTube (rot)

### Focus-Modus
- Serif-Überschrift "Focus-Modus"
- Untertitel "Bleib fokussiert und produktiv"
- Zentraler Timer (groß, Roboto Mono)
- "Minuten" Label unter Timer
- Duration Pills: 15m, 25m, 45m, 60m
- Primary Button: "Starten" mit Play-Icon
- Einstellungen Card:
  - Sound: "Am Ende abspielen"
  - Benachrichtigungen: "Bei Fokus-Ende"
- Bottom Navigation: Dashboard, Focus (aktiv), Badges, Social

### Challenges
- Serif-Überschrift "Challenges"
- Untertitel "Tritt gegen Freunde an"
- Primary Button: "+ Neue Challenge"
- Icon-Button: Share (nur Icon, kein Text)
- Aktive Challenges Section
  - Challenge Card: Icon, Titel, Beschreibung, Teilnehmer, Datum
- Challenge-Typen Grid

### Bottom Navigation
```
Items:           Dashboard, Focus, Badges, Social
Active:          #34D399 mit filled Icon
Inactive:        rgba(255,255,255,0.50) mit outline Icon
Background:      rgba(255,255,255,0.05) glass card
Height:          80dp (inkl. Safe Area)
```
