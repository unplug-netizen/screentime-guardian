//
//  FocusModeView.swift
//  ScreenTime Guardian - SwiftUI
//

import SwiftUI

struct FocusModeView: View {
    @State private var selectedDuration = 25
    @State private var isTimerRunning = false
    @State private var timeRemaining: TimeInterval = 25 * 60
    @State private var soundEnabled = true
    @State private var notificationsEnabled = true
    
    let durations = [15, 25, 45, 60]
    
    var timerText: String {
        let minutes = Int(timeRemaining) / 60
        let seconds = Int(timeRemaining) % 60
        return String(format: "%02d:%02d", minutes, seconds)
    }
    
    var body: some View {
        ScrollView {
            VStack(spacing: 32) {
                // Header
                VStack(spacing: 8) {
                    Text("Focus-Modus")
                        .font(.custom("PlayfairDisplay-Medium", size: 32))
                        .foregroundColor(.white)
                    
                    Text("Bleib fokussiert und produktiv")
                        .font(.system(size: 16))
                        .foregroundColor(.white.opacity(0.6))
                }
                
                // Timer Display
                ZStack {
                    Circle()
                        .fill(Color.white.opacity(0.03))
                        .frame(width: 280, height: 280)
                    
                    Circle()
                        .stroke(Color.white.opacity(0.05), lineWidth: 8)
                        .frame(width: 280, height: 280)
                    
                    VStack(spacing: 4) {
                        Text(timerText)
                            .font(.system(size: 64, weight: .bold, design: .monospaced))
                            .foregroundColor(.white)
                        
                        Text("Minuten")
                            .font(.system(size: 16))
                            .foregroundColor(.white.opacity(0.6))
                    }
                }
                
                // Duration Selector
                VStack(spacing: 16) {
                    Text("Dauer wählen")
                        .font(.system(size: 16))
                        .foregroundColor(.white.opacity(0.6))
                    
                    HStack(spacing: 12) {
                        ForEach(durations, id: \.self) { duration in
                            DurationPill(
                                duration: duration,
                                isSelected: duration == selectedDuration
                            ) {
                                selectedDuration = duration
                                timeRemaining = TimeInterval(duration * 60)
                            }
                        }
                    }
                }
                
                // Start Button
                Button(action: {
                    isTimerRunning.toggle()
                }) {
                    HStack {
                        Image(systemName: isTimerRunning ? "pause.fill" : "play.fill")
                        Text(isTimerRunning ? "Pause" : "Starten")
                    }
                    .font(.system(size: 18, weight: .bold))
                    .foregroundColor(Color(red: 0.04, green: 0.06, blue: 0.11))
                    .frame(maxWidth: .infinity)
                    .frame(height: 56)
                    .background(
                        Color(red: 0.2, green: 0.83, blue: 0.6)
                    )
                    .cornerRadius(28)
                }
                
                // Settings Card
                VStack(spacing: 0) {
                    ToggleRow(
                        icon: "speaker.wave.2.fill",
                        title: "Sound",
                        subtitle: "Am Ende abspielen",
                        isOn: $soundEnabled
                    )
                    
                    Divider()
                        .background(Color.white.opacity(0.1))
                    
                    ToggleRow(
                        icon: "bell.fill",
                        title: "Benachrichtigungen",
                        subtitle: "Bei Fokus-Ende",
                        isOn: $notificationsEnabled
                    )
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 16)
                        .fill(Color.white.opacity(0.05))
                        .overlay(
                            RoundedRectangle(cornerRadius: 16)
                                .stroke(Color.white.opacity(0.1), lineWidth: 1)
                        )
                )
            }
            .padding(.horizontal, 24)
            .padding(.top, 32)
        }
        .background(Color(red: 0.04, green: 0.06, blue: 0.11))
    }
}

struct DurationPill: View {
    let duration: Int
    let isSelected: Bool
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Text("\(duration)m")
                .font(.system(size: 16, weight: isSelected ? .bold : .medium, design: .monospaced))
                .foregroundColor(isSelected ? Color(red: 0.04, green: 0.06, blue: 0.11) : .white.opacity(0.7))
                .frame(minWidth: 64, minHeight: 44)
                .background(
                    Capsule()
                        .fill(isSelected ? Color(red: 0.2, green: 0.83, blue: 0.6) : Color.white.opacity(0.05))
                )
                .overlay(
                    Capsule()
                        .stroke(Color.white.opacity(isSelected ? 0 : 0.1), lineWidth: 1)
                )
        }
    }
}

struct ToggleRow: View {
    let icon: String
    let title: String
    let subtitle: String
    @Binding var isOn: Bool
    
    var body: some View {
        HStack(spacing: 16) {
            Image(systemName: icon)
                .font(.system(size: 18))
                .foregroundColor(Color(red: 0.2, green: 0.83, blue: 0.6))
                .frame(width: 40, height: 40)
                .background(Color(red: 0.2, green: 0.83, blue: 0.6).opacity(0.15))
                .cornerRadius(12)
            
            VStack(alignment: .leading, spacing: 2) {
                Text(title)
                    .font(.system(size: 16, weight: .medium))
                    .foregroundColor(.white)
                
                Text(subtitle)
                    .font(.system(size: 14))
                    .foregroundColor(.white.opacity(0.5))
            }
            
            Spacer()
            
            // Custom Toggle
            Toggle("", isOn: $isOn)
                .toggleStyle(SwitchToggleStyle(tint: Color(red: 0.2, green: 0.83, blue: 0.6)))
        }
        .padding(.vertical, 12)
    }
}

struct FocusModeView_Previews: PreviewProvider {
    static var previews: some View {
        FocusModeView()
    }
}
