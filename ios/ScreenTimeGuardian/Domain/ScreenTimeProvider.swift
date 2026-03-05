//
//  ScreenTimeProvider.swift
//  ScreenTime Guardian - Domain Layer
//

import Foundation
import Combine

// MARK: - ScreenTimeProvider Protocol
/// Abstrahiert Android UsageStatsManager und iOS ScreenTime API
public protocol ScreenTimeProvider: ObservableObject {
    // MARK: - Real-time Data
    func getCurrentSession() async throws -> AppSession?
    func getTodayUsage() async throws -> DailyUsage
    func getAppUsage(bundleIdentifier: String) async throws -> AppUsageStats?
    
    // MARK: - History (30 Days Local Cache)
    func getUsageHistory(days: Int) async throws -> [DailyUsage]
    func getCategoryUsage(_ category: AppCategory, days: Int) async throws -> CategoryUsage
    
    // MARK: - Limits
    func setAppLimit(bundleIdentifier: String, limitMinutes: Int) async throws
    func setCategoryLimit(_ category: AppCategory, limitMinutes: Int) async throws
    func removeLimit(id: String) async throws
    func getActiveLimits() async throws -> [LimitEntity]
    
    // MARK: - Events
    var limitReachedPublisher: AnyPublisher<LimitEvent, Never> { get }
    var unlockPublisher: AnyPublisher<UnlockEvent, Never> { get }
}

// MARK: - Data Models

public struct AppSession: Identifiable, Codable, Equatable {
    public let id: UUID
    public let bundleIdentifier: String
    public let appName: String
    public let category: AppCategory
    public let startTime: Date
    public let endTime: Date?
    public let durationSeconds: TimeInterval
    
    public init(id: UUID = UUID(), 
                bundleIdentifier: String, 
                appName: String, 
                category: AppCategory, 
                startTime: Date, 
                endTime: Date?, 
                durationSeconds: TimeInterval) {
        self.id = id
        self.bundleIdentifier = bundleIdentifier
        self.appName = appName
        self.category = category
        self.startTime = startTime
        self.endTime = endTime
        self.durationSeconds = durationSeconds
    }
    
    public var isActive: Bool { endTime == nil }
}

public struct DailyUsage: Identifiable, Codable, Equatable {
    public let id: String // YYYY-MM-DD
    public let date: Date
    public let totalScreenTimeSeconds: TimeInterval
    public let unlockCount: Int
    public let appUsage: [AppUsageBreakdown]
    public let categoryUsage: [AppCategory: TimeInterval]
    public let focusSessionsCompleted: Int
    public let focusTimeSeconds: TimeInterval
    
    public var totalScreenTimeHours: Double {
        totalScreenTimeSeconds / 3600
    }
    
    public var focusTimeMinutes: Double {
        focusTimeSeconds / 60
    }
    
    public init(date: Date, 
                totalScreenTimeSeconds: TimeInterval,
                unlockCount: Int,
                appUsage: [AppUsageBreakdown],
                categoryUsage: [AppCategory: TimeInterval],
                focusSessionsCompleted: Int,
                focusTimeSeconds: TimeInterval) {
        self.id = date.formatted(.iso8601.year().month().day())
        self.date = date
        self.totalScreenTimeSeconds = totalScreenTimeSeconds
        self.unlockCount = unlockCount
        self.appUsage = appUsage
        self.categoryUsage = categoryUsage
        self.focusSessionsCompleted = focusSessionsCompleted
        self.focusTimeSeconds = focusTimeSeconds
    }
}

public struct AppUsageBreakdown: Codable, Equatable {
    public let bundleIdentifier: String
    public let appName: String
    public let category: AppCategory
    public let usageSeconds: TimeInterval
    public let percentageOfTotal: Float
}

public struct AppUsageStats: Codable, Equatable {
    public let bundleIdentifier: String
    public let appName: String
    public let category: AppCategory
    public let totalUsageToday: TimeInterval
    public let lastUsed: Date?
}

public struct CategoryUsage: Codable, Equatable {
    public let category: AppCategory
    public let totalSeconds: TimeInterval
    public let topApps: [AppUsageBreakdown]
}

// MARK: - Limit Models

public struct LimitEntity: Identifiable, Codable, Equatable {
    public let id: String
    public let type: LimitType
    public let targetId: String // bundleIdentifier oder category.rawValue
    public let targetName: String
    public let limitMinutes: Int
    public let createdAt: Date
    public var isActive: Bool
    public var currentUsageMinutes: Int?
    
    public init(id: String = UUID().uuidString,
                type: LimitType,
                targetId: String,
                targetName: String,
                limitMinutes: Int,
                createdAt: Date = Date(),
                isActive: Bool = true,
                currentUsageMinutes: Int? = nil) {
        self.id = id
        self.type = type
        self.targetId = targetId
        self.targetName = targetName
        self.limitMinutes = limitMinutes
        self.createdAt = createdAt
        self.isActive = isActive
        self.currentUsageMinutes = currentUsageMinutes
    }
}

public enum LimitType: String, Codable, Equatable {
    case app
    case category
}

// MARK: - App Category (System Categories)

public enum AppCategory: String, Codable, CaseIterable, Equatable {
    case games = "GAMES"
    case social = "SOCIAL"
    case productivity = "PRODUCTIVITY"
    case entertainment = "ENTERTAINMENT"
    case education = "EDUCATION"
    case health = "HEALTH"
    case news = "NEWS"
    case shopping = "SHOPPING"
    case finance = "FINANCE"
    case other = "OTHER"
    
    public var displayName: String {
        switch self {
        case .games: return "Games"
        case .social: return "Social"
        case .productivity: return "Productivity"
        case .entertainment: return "Entertainment"
        case .education: return "Education"
        case .health: return "Health"
        case .news: return "News"
        case .shopping: return "Shopping"
        case .finance: return "Finance"
        case .other: return "Other"
        }
    }
    
    public var icon: String {
        switch self {
        case .games: return "gamecontroller.fill"
        case .social: return "bubble.left.and.bubble.right.fill"
        case .productivity: return "checkmark.square.fill"
        case .entertainment: return "play.rectangle.fill"
        case .education: return "book.fill"
        case .health: return "heart.fill"
        case .news: return "newspaper.fill"
        case .shopping: return "bag.fill"
        case .finance: return "dollarsign.circle.fill"
        case .other: return "app.fill"
        }
    }
}

// MARK: - Events

public struct LimitEvent: Codable, Equatable {
    public let limitId: String
    public let type: LimitType
    public let targetName: String
    public let limitMinutes: Int
    public let actualUsageMinutes: Int
    public let timestamp: Date
}

public struct UnlockEvent: Codable, Equatable {
    public let limitId: String
    public let unlockedAt: Date
    public let unlockMethod: UnlockMethod
}

public enum UnlockMethod: String, Codable, Equatable {
    case biometric = "BIOMETRIC"
    case pin = "PIN"
    case emergency = "EMERGENCY"
}
