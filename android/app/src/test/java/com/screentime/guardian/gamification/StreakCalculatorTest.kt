package com.screentime.guardian.gamification

import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDate

class StreakCalculatorTest {
    
    private val calculator = StreakCalculator()
    
    @Test
    fun `calculateStreak returns zero for empty history`() {
        val result = calculator.calculateStreak(
            history = emptyList(),
            streakType = StreakType.DAILY_FOCUS,
            goalMinutes = 360
        )
        
        assertEquals(0, result.currentStreak)
        assertEquals(0, result.longestStreak)
        assertNull(result.lastActiveDate)
    }
    
    @Test
    fun `calculateStreak calculates current streak correctly`() {
        val today = LocalDate.now()
        val history = listOf(
            DailyActivity(today, 400, 0.05f, 1, true),
            DailyActivity(today.minusDays(1), 380, 0.05f, 1, true),
            DailyActivity(today.minusDays(2), 370, 0.05f, 1, true)
        )
        
        val result = calculator.calculateStreak(
            history = history,
            streakType = StreakType.DAILY_FOCUS,
            goalMinutes = 360
        )
        
        assertEquals(3, result.currentStreak)
        assertEquals(3, result.longestStreak)
        assertEquals(today, result.lastActiveDate)
    }
    
    @Test
    fun `calculateStreak breaks streak on missed day`() {
        val today = LocalDate.now()
        val history = listOf(
            DailyActivity(today, 400, 0.05f, 1, true),
            // Day skipped
            DailyActivity(today.minusDays(2), 380, 0.05f, 1, true)
        )
        
        val result = calculator.calculateStreak(
            history = history,
            streakType = StreakType.DAILY_FOCUS,
            goalMinutes = 360
        )
        
        assertEquals(1, result.currentStreak) // Only today counts
        assertEquals(1, result.longestStreak)
    }
    
    @Test
    fun `calculateStreak handles distraction free streak`() {
        val today = LocalDate.now()
        val history = listOf(
            DailyActivity(today, 300, 0.05f, 1, true),
            DailyActivity(today.minusDays(1), 300, 0.08f, 1, true),
            DailyActivity(today.minusDays(2), 300, 0.15f, 1, true) // Over 10%
        )
        
        val result = calculator.calculateStreak(
            history = history,
            streakType = StreakType.DISTRACTION_FREE,
            goalMinutes = 0
        )
        
        assertEquals(2, result.currentStreak) // Last 2 days under 10%
        assertEquals(2, result.longestStreak)
    }
    
    @Test
    fun `canExtendStreakToday returns true for new streak`() {
        val canExtend = calculator.canExtendStreakToday(
            lastActiveDate = null,
            streakType = StreakType.DAILY_FOCUS,
            todayProgress = DailyActivity(LocalDate.now(), 0, 0f, 0, false)
        )
        
        assertTrue(canExtend)
    }
    
    @Test
    fun `canExtendStreakToday returns false if goal already met`() {
        val today = LocalDate.now()
        val canExtend = calculator.canExtendStreakToday(
            lastActiveDate = today.minusDays(1),
            streakType = StreakType.DAILY_FOCUS,
            todayProgress = DailyActivity(today, 400, 0f, 1, true)
        )
        
        assertFalse(canExtend) // Goal already met
    }
}

class BadgeEngineTest {
    
    private val engine = BadgeEngine()
    
    @Test
    fun `checkBadges unlocks first focus badge`() {
        val focusSessions = listOf(
            FocusSession("1", 25, 9, "2026-03-06")
        )
        
        val badges = engine.checkBadges(
            history = emptyList(),
            focusSessions = focusSessions,
            currentStreaks = emptyMap()
        )
        
        val firstStepsBadge = badges.find { it.id == "first_focus" }
        assertNotNull(firstStepsBadge)
        assertTrue(firstStepsBadge?.isUnlocked == true)
    }
    
    @Test
    fun `calculateScore returns correct value`() {
        val dailyUsage = DailyUsage(
            date = LocalDate.now(),
            totalScreenTimeSeconds = 4 * 3600, // 4 hours
            unlockCount = 10,
            appUsage = emptyList(),
            categoryUsage = emptyMap(),
            focusSessionsCompleted = 3,
            focusTimeSeconds = 3 * 3600 // 3 hours = 180 minutes
        )
        
        val score = engine.calculateScore(dailyUsage)
        
        // Score = (100 - 4) + 180 = 276
        assertEquals(276, score)
    }
    
    @Test
    fun `anonymizeUserId returns consistent hash`() {
        val userId = "test-user-123"
        
        val hash1 = engine.anonymizeUserId(userId)
        val hash2 = engine.anonymizeUserId(userId)
        
        assertEquals(hash1, hash2)
        assertTrue(hash1.startsWith("User #"))
        assertEquals(10, hash1.length) // "User #" + 4 chars
    }
    
    @Test
    fun `checkBadges calculates progress for week warrior`() {
        val streaks = mapOf(StreakType.DAILY_FOCUS to 5)
        
        val badges = engine.checkBadges(
            history = emptyList(),
            focusSessions = emptyList(),
            currentStreaks = streaks
        )
        
        val weekWarriorBadge = badges.find { it.id == "week_warrior" }
        assertNotNull(weekWarriorBadge)
        assertEquals(0.71f, weekWarriorBadge?.progress ?: 0f, 0.01f) // 5/7
    }
}
