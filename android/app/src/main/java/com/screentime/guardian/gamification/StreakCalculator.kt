package com.screentime.guardian.gamification

import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * Streak-Berechnung mit einfachen Tagesvergleichen
 * Keine Zeitzonen-Komplexität - 00:00-23:59 Systemzeit
 */
class StreakCalculator {
    
    /**
     * Berechnet aktuelle und längste Streak
     */
    fun calculateStreak(
        history: List<DailyActivity>,
        streakType: StreakType,
        goalMinutes: Int
    ): StreakResult {
        if (history.isEmpty()) {
            return StreakResult(0, 0, null)
        }
        
        val sortedDays = history.sortedByDescending { it.date }
        val today = LocalDate.now()
        
        var currentStreak = 0
        var longestStreak = 0
        var tempStreak = 0
        var lastActiveDate: LocalDate? = null
        
        var previousDate: LocalDate? = null
        
        for (day in sortedDays) {
            val metGoal = day.meetsStreakGoal(streakType, goalMinutes)
            
            if (metGoal) {
                // Prüfe ob Tage aufeinanderfolgen
                val isConsecutive = previousDate == null || 
                    ChronoUnit.DAYS.between(day.date, previousDate) == 1L
                
                if (isConsecutive) {
                    tempStreak++
                } else {
                    tempStreak = 1
                }
                
                // Aktuelle Streak nur wenn bis heute/yesterday geht
                if (currentStreak == 0) {
                    val daysSince = ChronoUnit.DAYS.between(day.date, today)
                    if (daysSince <= 1) { // Heute oder gestern
                        currentStreak = tempStreak
                        lastActiveDate = day.date
                    }
                }
                
                longestStreak = maxOf(longestStreak, tempStreak)
                previousDate = day.date
            } else {
                tempStreak = 0
            }
        }
        
        return StreakResult(currentStreak, longestStreak, lastActiveDate)
    }
    
    /**
     * Prüft ob Streak heute erweitert werden kann
     */
    fun canExtendStreakToday(
        lastActiveDate: LocalDate?,
        streakType: StreakType,
        todayProgress: DailyActivity
    ): Boolean {
        if (lastActiveDate == null) return true // Neue Streak möglich
        
        val today = LocalDate.now()
        val daysSince = ChronoUnit.DAYS.between(lastActiveDate, today)
        
        // Streak kann erweitert werden wenn:
        // - Letzte Aktivität war gestern (daysSince == 1) oder heute (daysSince == 0)
        // - Heutiges Ziel noch nicht erreicht
        return daysSince <= 1 && !todayProgress.meetsStreakGoal(streakType, getGoalForType(streakType))
    }
    
    private fun getGoalForType(type: StreakType): Int {
        return when (type) {
            StreakType.DAILY_FOCUS -> 360 // 6 Stunden Default
            StreakType.DISTRACTION_FREE -> 0 // Kein spezifisches Minuten-Ziel
            StreakType.EARLY_START -> 1 // Mindestens 1 Session
        }
    }
}

data class DailyActivity(
    val date: LocalDate,
    val focusTimeMinutes: Int,
    val distractionPercentage: Float,
    val sessionsBeforeNoon: Int,
    val limitsAdhered: Boolean
) {
    fun meetsStreakGoal(type: StreakType, goalMinutes: Int): Boolean {
        return when (type) {
            StreakType.DAILY_FOCUS -> focusTimeMinutes >= goalMinutes
            StreakType.DISTRACTION_FREE -> distractionPercentage < 0.10f
            StreakType.EARLY_START -> sessionsBeforeNoon > 0
        }
    }
}

data class StreakResult(
    val currentStreak: Int,
    val longestStreak: Int,
    val lastActiveDate: LocalDate?
)

enum class StreakType {
    DAILY_FOCUS,      // Jeden Tag Focus-Ziel erreicht
    DISTRACTION_FREE, // Täglich unter Limit geblieben
    EARLY_START       // Vor 12 Uhr gestartet
}
