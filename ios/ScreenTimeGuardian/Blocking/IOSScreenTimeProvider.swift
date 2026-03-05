//
//  ScreenTimeData.swift
//  ScreenTime Guardian - iOS ScreenTime API Implementation
//

import Foundation
import ScreenTime
import FamilyControls
import DeviceActivity
import ManagedSettings
import Combine

/// iOS Implementation des ScreenTimeProvider
@available(iOS 15.0, *)
public class IOSScreenTimeProvider: NSObject, ScreenTimeProvider {
    
    // MARK: - Properties
    private let center = DeviceActivityCenter()
    private let store = ManagedSettingsStore()
    private var cancellables = Set<AnyCancellable>()
    
    private let limitReachedSubject = PassthroughSubject<LimitEvent, Never>()
    private let unlockSubject = PassthroughSubject<UnlockEvent, Never>()
    
    public var limitReachedPublisher: AnyPublisher<LimitEvent, Never> {
        limitReachedSubject.eraseToAnyPublisher()
    }
    
    public var unlockPublisher: AnyPublisher<UnlockEvent, Never> {
        unlockSubject.eraseToAnyPublisher()
    }
    
    // MARK: - Initialization
    public override init() {
        super.init()
        setupDeviceActivityMonitoring()
    }
    
    // MARK: - ScreenTimeProvider Implementation
    
    public func getCurrentSession() async throws -> AppSession? {
        // iOS gibt keine Echtzeit-Session, nur historische Daten
        return nil
    }
    
    public func getTodayUsage() async throws -> DailyUsage {
        // Implementation nutzt DeviceActivity Framework
        // Für Demo: Return placeholder
        return DailyUsage(
            date: Date(),
            totalScreenTimeSeconds: 4 * 3600, // 4 hours placeholder
            unlockCount: 45,
            appUsage: [],
            categoryUsage: [:],
            focusSessionsCompleted: 3,
            focusTimeSeconds: 3 * 3600
        )
    }
    
    public func getAppUsage(bundleIdentifier: String) async throws -> AppUsageStats? {
        // Query DeviceActivity für spezifische App
        return nil
    }
    
    public func getUsageHistory(days: Int) async throws -> [DailyUsage] {
        // Lade aus CoreData oder DeviceActivity
        return []
    }
    
    public func getCategoryUsage(_ category: AppCategory, days: Int) async throws -> CategoryUsage {
        return CategoryUsage(
            category: category,
            totalSeconds: 0,
            topApps: []
        )
    }
    
    public func setAppLimit(bundleIdentifier: String, limitMinutes: Int) async throws {
        // Erstelle Device Activity Schedule
        let schedule = DeviceActivitySchedule(
            intervalStart: DateComponents(hour: 0, minute: 0),
            intervalEnd: DateComponents(hour: 23, minute: 59),
            repeats: true,
            warningTime: DateComponents(minute: 5)
        )
        
        let eventName = DeviceActivityEvent.Name(bundleIdentifier)
        let event = DeviceActivityEvent(
            applications: [ApplicationToken(bundleIdentifier: bundleIdentifier)!],
            threshold: DateComponents(minute: limitMinutes)
        )
        
        do {
            try center.startMonitoring(
                [.daily],
                during: schedule
            )
        } catch {
            throw ScreenTimeError.limitSetupFailed(error.localizedDescription)
        }
    }
    
    public func setCategoryLimit(_ category: AppCategory, limitMinutes: Int) async throws {
        // Map AppCategory zu ScreenTime-Kategorie
        let categoryToken = mapToCategoryToken(category)
        
        let schedule = DeviceActivitySchedule(
            intervalStart: DateComponents(hour: 0, minute: 0),
            intervalEnd: DateComponents(hour: 23, minute: 59),
            repeats: true
        )
        
        // Implementation
    }
    
    public func removeLimit(id: String) async throws {
        center.stopMonitoring([DeviceActivityName(id)])
    }
    
    public func getActiveLimits() async throws -> [LimitEntity] {
        // Lade aus CoreData
        return []
    }
    
    // MARK: - Private Methods
    
    private func setupDeviceActivityMonitoring() {
        // Handle Device Activity Events
        // Implementation für ShieldConfiguration
    }
    
    private func mapToCategoryToken(_ category: AppCategory) -> ActivityCategoryToken? {
        // Map interne Kategorie zu ScreenTime-Kategorie
        return nil
    }
}

// MARK: - Errors

public enum ScreenTimeError: Error {
    case authorizationDenied
    case limitSetupFailed(String)
    case dataUnavailable
}

// MARK: - Shield Extension Support

/// Wird im Shield Extension Target genutzt
@available(iOS 15.0, *)
public class ShieldConfigurationProvider: ShieldConfigurationDataSource {
    
    override func configuration(shielding application: Application) -> ShieldConfiguration {
        ShieldConfiguration(
            backgroundBlurStyle: .dark,
            backgroundColor: UIColor(red: 0.04, green: 0.06, blue: 0.11, alpha: 1.0),
            icon: UIImage(systemName: "hourglass.circle.fill"),
            title: ShieldConfiguration.Text("Time Limit Reached"),
            subtitle: ShieldConfiguration.Text("You've reached your daily limit for \(application.localizedDisplayName ?? "this app")"),
            primaryButtonLabel: ShieldConfiguration.Text("Unlock with Biometric"),
            primaryButtonBackgroundColor: UIColor(red: 0.20, green: 0.83, blue: 0.60, alpha: 0.2),
            secondaryButtonLabel: ShieldConfiguration.Text("Use PIN")
        )
    }
    
    override func configuration(shielding application: Application, in category: ActivityCategory) -> ShieldConfiguration {
        ShieldConfiguration(
            backgroundBlurStyle: .dark,
            backgroundColor: UIColor(red: 0.04, green: 0.06, blue: 0.11, alpha: 1.0),
            icon: UIImage(systemName: "hourglass.circle.fill"),
            title: ShieldConfiguration.Text("Category Limit Reached"),
            subtitle: ShieldConfiguration.Text("You've reached your daily limit for \(category.localizedDisplayName ?? "this category")"),
            primaryButtonLabel: ShieldConfiguration.Text("Unlock with Biometric"),
            primaryButtonBackgroundColor: UIColor(red: 0.20, green: 0.83, blue: 0.60, alpha: 0.2),
            secondaryButtonLabel: ShieldConfiguration.Text("Use PIN")
        )
    }
}
