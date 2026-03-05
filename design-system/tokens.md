# Glassmorphism Design System

## Design Tokens

### Farben

#### Dark Mode (Primary)
```
Background Primary:    #0A0F1C
Background Secondary:  #12182B
Background Tertiary:   #1A2340

Glass Surface:         rgba(255, 255, 255, 0.07)
Glass Border:          rgba(255, 255, 255, 0.10)
Glass Highlight:       rgba(255, 255, 255, 0.15)

Accent Primary:        #34D399 (Emerald 400)
Accent Secondary:      #10B981 (Emerald 500)
Accent Tertiary:       #6EE7B7 (Emerald 300)

Text Primary:          #FAFAFA
Text Secondary:        rgba(250, 250, 250, 0.70)
Text Tertiary:         rgba(250, 250, 250, 0.50)

Success:               #34D399
Warning:               #FBBF24
Error:                 #F87171
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
H1:     32px / 40px line-height / -0.5px letter-spacing
H2:     24px / 32px line-height / -0.3px letter-spacing
H3:     20px / 28px line-height / -0.2px letter-spacing

Stats:  48px / 56px line-height / -1px letter-spacing (tabular-nums)
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
Button Press:   scale(0.95), 100ms spring
Card Hover:     translateY(-2px), 200ms spring
Progress Ring:  stroke-dashoffset animation, 1s ease-out
Badge Unlock:   scale(0.5→1.1→1.0) + rotate(0→5→0°), 600ms spring
```

### Glassmorphism-Komponenten

#### Glass Card
```
Background:      rgba(255, 255, 255, 0.07)
Backdrop-Blur:   20px
Border:          1px solid rgba(255, 255, 255, 0.10)
Border-Radius:   16px
Shadow:          0 8px 32px rgba(0, 0, 0, 0.12)
```

#### Glass Button (Primary)
```
Background:      rgba(52, 211, 153, 0.20)
Backdrop-Blur:   10px
Border:          1px solid rgba(52, 211, 153, 0.30)
Border-Radius:   12px
Text:            #34D399
Press-State:     scale(0.95), background rgba(52, 211, 153, 0.30)
```

#### Glass Input
```
Background:      rgba(255, 255, 255, 0.05)
Border:          1px solid rgba(255, 255, 255, 0.08)
Border-Radius:   12px
Focus-State:     border-color rgba(52, 211, 153, 0.50)
```

## Screen-Spezifikationen

### Dashboard
- Radial-Progress für Daily Goal (200px Durchmesser, 12px stroke)
- Horizontal scrolling "Today-Stats" als Glass-Cards
- Floating Action Button für Quick-Focus (Bottom-Center)

### Focus-Modus
- Fullscreen-Timer mit animiertem Ring (CAShapeLayer / Canvas)
- Hintergrund: Animated Gradient (subtle)
- Controls: Glass-Buttons, bottom-aligned

### Badges
- 3D-Stack-Visualisierung (wie Apple Wallet)
- Monochrom (grayscale) bis freigeschaltet
- Unlock-Animation: Scale + Glow + Confetti (subtle)

### Leaderboard
- List-View mit anonymen User-Hashes
- Top 3: Glass-Cards mit Gold/Silver/Bronze Accent
- Self-Highlight: Primary Accent Border
