//
//  ScreenTimeRepository.swift
//  ScreenTime Guardian - Repository Layer
//

import Foundation
import Combine
import CoreData

// MARK: - ScreenTimeRepository
/// Local-First Repository mit 30 Tagen Historie
public actor ScreenTimeRepository {
    
    // MARK: - Properties
    private let localDataSource: LocalScreenTimeDataSource
    private let remoteDataSource: RemoteScreenTimeDataSource?
    private let syncManager: SyncManager
    
    // MARK: - Initialization
    public init(
        localDataSource: LocalScreenTimeDataSource,
        remoteDataSource: RemoteScreenTimeDataSource? = nil,
        syncManager: SyncManager = SyncManager()
    ) {
        self.localDataSource = localDataSource
        self.remoteDataSource = remoteDataSource
        self.syncManager = syncManager
    }
    
    // MARK: - Public Methods
    
    /// Speichert DailyUsage lokal und trigger Sync
    public func saveDailyUsage(_ usage: DailyUsage) async throws {
        try await localDataSource.saveDailyUsage(usage)
        
        // Sync zu Firebase (nur aggregierte Daten)
        if let remote = remoteDataSource {
            let syncData = usage.toSyncData()
            try? await remote.syncDailyStats(syncData)
        }
    }
    
    /// Holt Historie für die letzten X Tage
    public func getUsageHistory(days: Int = 30) async throws -> [DailyUsage] {
        let calendar = Calendar.current
        let endDate = Date()
        guard let startDate = calendar.date(byAdding: .day, value: -days, to: endDate) else {
            return []
        }
        
        return try await localDataSource.fetchUsageHistory(from: startDate, to: endDate)
    }
    
    /// Berechnet Streaks basierend auf lokalen Daten
    public func calculateStreak(type: StreakType, goalMinutes: Int) async -> Streak {
        let history = try? await getUsageHistory(days: 365) // Max für longest streak
        guard let days = history, !days.isEmpty else {
            return Streak(currentStreak: 0, longestStreak: 0, lastActiveDate: nil, streakType: type)
        }
        
        let sortedDays = days.sorted { $0.date > $1.date }
        var currentStreak = 0
        var longestStreak = 0
        var tempStreak = 0
        var lastActiveDate: Date?
        
        let calendar = Calendar.current
        var previousDate: Date?
        
        for day in sortedDays {
            let metGoal = didMeetStreakGoal(day: day, type: type, goalMinutes: goalMinutes)
            
            if metGoal {
                if let prev = previousDate,
                   calendar.isDate(day.date, inSameDayAs: calendar.date(byAdding: .day, value: -1, to: prev)!) {
                    tempStreak += 1
                } else {
                    tempStreak = 1
                }
                
                if currentStreak == 0 && currentStreak < tempStreak {
                    currentStreak = tempStreak
                    lastActiveDate = day.date
                }
                
                longestStreak = max(longestStreak, tempStreak)
                previousDate = day.date
            } else {
                if currentStreak == 0 {
                    // Streak gebrochen heute
                    break
                }
                tempStreak = 0
            }
        }
        
        return Streak(
            currentStreak: currentStreak,
            longestStreak: longestStreak,
            lastActiveDate: lastActiveDate,
            streakType: type
        )
    }
    
    // MARK: - Private Helpers
    
    private func didMeetStreakGoal(day: DailyUsage, type: StreakType, goalMinutes: Int) -> Bool {
        switch type {
        case .dailyFocus:
            return day.focusTimeSeconds >= TimeInterval(goalMinutes * 60)
        case .distractionFree:
            // Implementation depends on limit adherence tracking
            return true // Placeholder
        case .earlyStart:
            return day.focusSessionsCompleted > 0 // Simplification
        }
    }
}

// MARK: - Data Source Protocols

public protocol LocalScreenTimeDataSource {
    func saveDailyUsage(_ usage: DailyUsage) async throws
    func fetchUsageHistory(from: Date, to: Date) async throws -> [DailyUsage]
    func fetchDailyUsage(for date: Date) async throws -> DailyUsage?
    func saveLimit(_ limit: LimitEntity) async throws
    func fetchActiveLimits() async throws -> [LimitEntity]
    func deleteLimit(id: String) async throws
}

public protocol RemoteScreenTimeDataSource {
    func syncDailyStats(_ stats: AggregatedDailyStats) async throws
    func fetchLeaderboard() async throws -> [LeaderboardEntry]
}

// MARK: - CoreData Implementation

public actor CoreDataScreenTimeDataSource: LocalScreenTimeDataSource {
    
    private let container: NSPersistentContainer
    
    public init(container: NSPersistentContainer) {
        self.container = container
    }
    
    public func saveDailyUsage(_ usage: DailyUsage) async throws {
        let context = container.newBackgroundContext()
        try await context.perform {
            let entity = DailyUsageEntity(context: context)
            entity.id = usage.id
            entity.date = usage.date
            entity.totalScreenTimeSeconds = usage.totalScreenTimeSeconds
            entity.unlockCount = Int32(usage.unlockCount)
            entity.focusSessionsCompleted = Int32(usage.focusSessionsCompleted)
            entity.focusTimeSeconds = usage.focusTimeSeconds
            
            try context.save()
        }
    }
    
    public func fetchUsageHistory(from: Date, to: Date) async throws -> [DailyUsage] {
        let context = container.viewContext
        let request: NSFetchRequest<DailyUsageEntity> = DailyUsageEntity.fetchRequest()
        request.predicate = NSPredicate(format: "date >= %@ AND date <= %@", from as NSDate, to as NSDate)
        request.sortDescriptors = [NSSortDescriptor(key: "date", ascending: false)]
        
        let entities = try context.fetch(request)
        return entities.map { $0.toDomainModel() }
    }
    
    public func fetchDailyUsage(for date: Date) async throws -> DailyUsage? {
        let context = container.viewContext
        let request: NSFetchRequest<DailyUsageEntity> = DailyUsageEntity.fetchRequest()
        
        let calendar = Calendar.current
        let startOfDay = calendar.startOfDay(for: date)
        let endOfDay = calendar.date(byAdding: .day, value: 1, to: startOfDay)!
        
        request.predicate = NSPredicate(format: "date >= %@ AND date < %@", startOfDay as NSDate, endOfDay as NSDate)
        request.fetchLimit = 1
        
        let entities = try context.fetch(request)
        return entities.first?.toDomainModel()
    }
    
    public func saveLimit(_ limit: LimitEntity) async throws {
        // Implementation
    }
    
    public func fetchActiveLimits() async throws -> [LimitEntity] {
        // Implementation
        return []
    }
    
    public func deleteLimit(id: String) async throws {
        // Implementation
    }
}

// MARK: - Entity Extensions

extension DailyUsageEntity {
    func toDomainModel() -> DailyUsage {
        DailyUsage(
            date: date ?? Date(),
            totalScreenTimeSeconds: totalScreenTimeSeconds,
            unlockCount: Int(unlockCount),
            appUsage: [], // Parse from JSON or relation
            categoryUsage: [:],
            focusSessionsCompleted: Int(focusSessionsCompleted),
            focusTimeSeconds: focusTimeSeconds
        )
    }
}
