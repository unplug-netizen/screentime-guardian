//
//  DashboardView.swift
//  ScreenTime Guardian - SwiftUI
//

import SwiftUI
import Combine

struct DashboardView: View {
    @StateObject private var viewModel = DashboardViewModel()
    
    var body: some View {
        ScrollView {
            VStack(spacing: 24) {
                // Circular Progress Ring
                CircularProgressRing(
                    progress: viewModel.dailyProgress,
                    currentTime: viewModel.currentScreenTime,
                    goalTime: "von \(viewModel.dailyGoalTime)",
                    remainingTime: viewModel.remainingTime
                )
                .frame(width: 280, height: 280)
                
                // Stats Row
                HStack(spacing: 12) {
                    StatCard(
                        icon: "lock.fill",
                        value: "\(viewModel.unlockCount)",
                        label: "Entsperrungen"
                    )
                    
                    StatCard(
                        icon: "target",
                        value: "\(viewModel.goalAchievement)%",
                        label: "Ziel erreicht"
                    )
                }
                
                // App Usage Section
                VStack(alignment: .leading, spacing: 16) {
                    HStack {
                        Text("App-Nutzung")
                            .font(.custom("PlayfairDisplay-Medium", size: 20))
                            .foregroundColor(.white)
                        
                        Spacer()
                        
                        Button(action: {}) {
                            HStack {
                                Text("Details")
                                    .font(.system(size: 16, weight: .medium))
                                    .foregroundColor(Color(red: 0.2, green: 0.83, blue: 0.6))
                                Image(systemName: "chevron.right")
                                    .foregroundColor(Color(red: 0.2, green: 0.83, blue: 0.6))
                            }
                        }
                    }
                    
                    VStack(spacing: 0) {
                        ForEach(viewModel.appUsageList) { app in
                            AppUsageRow(app: app)
                            if app.id != viewModel.appUsageList.last?.id {
                                Divider()
                                    .background(Color.white.opacity(0.1))
                            }
                        }
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
            }
            .padding(.horizontal, 20)
            .padding(.top, 24)
        }
        .background(Color(red: 0.04, green: 0.06, blue: 0.11))
    }
}

struct CircularProgressRing: View {
    let progress: Double
    let currentTime: String
    let goalTime: String
    let remainingTime: String
    
    @State private var animatedProgress: Double = 0
    
    var body: some View {
        ZStack {
            // Glow effect
            Circle()
                .fill(
                    RadialGradient(
                        gradient: Gradient(colors: [
                            Color(red: 0.2, green: 0.83, blue: 0.6).opacity(0.3),
                            Color.clear
                        ]),
                        center: .center,
                        startRadius: 100,
                        endRadius: 140
                    )
                )
            
            // Background circle
            Circle()
                .stroke(Color.white.opacity(0.05), lineWidth: 18)
            
            // Progress circle
            Circle()
                .trim(from: 0, to: animatedProgress)
                .stroke(
                    AngularGradient(
                        gradient: Gradient(colors: [
                            Color(red: 0.2, green: 0.83, blue: 0.6),
                            Color(red: 0.06, green: 0.73, blue: 0.5),
                            Color(red: 0.2, green: 0.83, blue: 0.6)
                        ]),
                        center: .center
                    ),
                    style: StrokeStyle(lineWidth: 18, lineCap: .round)
                )
                .rotationEffect(.degrees(-90))
                .animation(.spring(response: 1.5, dampingFraction: 0.7), value: animatedProgress)
            
            // Center content
            VStack(spacing: 4) {
                Text(currentTime)
                    .font(.system(size: 40, weight: .bold, design: .monospaced))
                    .foregroundColor(.white)
                
                Text(goalTime)
                    .font(.system(size: 16))
                    .foregroundColor(.white.opacity(0.6))
                
                Text(remainingTime)
                    .font(.system(size: 16, weight: .medium))
                    .foregroundColor(Color(red: 0.2, green: 0.83, blue: 0.6))
                    .padding(.top, 4)
            }
        }
        .onAppear {
            animatedProgress = progress
        }
    }
}

struct StatCard: View {
    let icon: String
    let value: String
    let label: String
    
    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            HStack(alignment: .top) {
                Image(systemName: icon)
                    .font(.system(size: 20))
                    .foregroundColor(Color(red: 0.2, green: 0.83, blue: 0.6))
                    .frame(width: 48, height: 48)
                    .background(Color.white.opacity(0.08))
                    .cornerRadius(12)
                
                Spacer()
            }
            
            VStack(alignment: .leading, spacing: 2) {
                Text(value)
                    .font(.system(size: 24, weight: .bold, design: .monospaced))
                    .foregroundColor(.white)
                
                Text(label)
                    .font(.system(size: 14))
                    .foregroundColor(.white.opacity(0.6))
            }
        }
        .padding(16)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(
            RoundedRectangle(cornerRadius: 16)
                .fill(Color.white.opacity(0.05))
                .overlay(
                    RoundedRectangle(cornerRadius: 16)
                        .stroke(Color.white.opacity(0.1), lineWidth: 1)
                )
        )
    }
}

struct AppUsageRow: View {
    let app: AppUsageItem
    
    var progressColor: Color {
        switch app.category {
        case .social: return Color(red: 0.93, green: 0.27, blue: 0.6) // Pink
        case .messaging: return Color(red: 0.06, green: 0.73, blue: 0.5) // Green
        case .video: return Color(red: 0.94, green: 0.27, blue: 0.27) // Red
        default: return Color(red: 0.23, green: 0.51, blue: 0.96) // Blue
        }
    }
    
    var body: some View {
        HStack(spacing: 16) {
            // App Icon
            Text(app.appName.prefix(1))
                .font(.system(size: 18, weight: .bold))
                .foregroundColor(progressColor)
                .frame(width: 44, height: 44)
                .background(progressColor.opacity(0.15))
                .clipShape(Circle())
            
            VStack(alignment: .leading, spacing: 6) {
                Text(app.appName)
                    .font(.system(size: 16, weight: .medium))
                    .foregroundColor(.white)
                
                // Progress bar
                GeometryReader { geo in
                    ZStack(alignment: .leading) {
                        Rectangle()
                            .fill(Color.white.opacity(0.08))
                            .frame(height: 6)
                            .cornerRadius(3)
                        
                        Rectangle()
                            .fill(progressColor)
                            .frame(width: geo.size.width * app.progress, height: 6)
                            .cornerRadius(3)
                    }
                }
                .frame(height: 6)
            }
            
            Spacer()
            
            Text(app.formattedTime)
                .font(.system(size: 16, weight: .medium, design: .monospaced))
                .foregroundColor(.white.opacity(0.8))
        }
        .padding(.vertical, 8)
    }
}

class DashboardViewModel: ObservableObject {
    @Published var dailyProgress: Double = 0.875
    @Published var currentScreenTime = "3h 30m"
    @Published var dailyGoalTime = "4h 0m"
    @Published var remainingTime = "30m verbleibend"
    @Published var unlockCount = 47
    @Published var goalAchievement = 88
    @Published var appUsageList: [AppUsageItem] = [
        AppUsageItem(id: "1", appName: "Instagram", category: .social, formattedTime: "1h 0m", progress: 0.65),
        AppUsageItem(id: "2", appName: "WhatsApp", category: .messaging, formattedTime: "45m", progress: 0.45),
        AppUsageItem(id: "3", appName: "YouTube", category: .video, formattedTime: "40m", progress: 0.40)
    ]
}

struct AppUsageItem: Identifiable {
    let id: String
    let appName: String
    let category: AppCategory
    let formattedTime: String
    let progress: Double
}

enum AppCategory {
    case social, messaging, video, productivity, other
}

struct DashboardView_Previews: PreviewProvider {
    static var previews: some View {
        DashboardView()
    }
}
