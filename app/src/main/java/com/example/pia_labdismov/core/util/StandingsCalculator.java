package com.example.pia_labdismov.core.util;

/**
 * Pure Java engine for calculating volleyball competition standings and FIVB tie-breakers.
 * Tie-breaker criteria:
 * 1. Total Points
 * 2. Number of Matches Won (PG)
 * 3. Set Ratio (Sets Won / Sets Lost)
 * 4. Point Ratio (Points Won / Points Lost)
 */
public final class StandingsCalculator {

    private StandingsCalculator() {
        // Private constructor
    }

    /**
     * Calculates Set Ratio: Sets Won / Sets Lost (handles division by zero).
     */
    public static double calculateSetRatio(int setsWon, int setsLost) {
        if (setsLost == 0) {
            return setsWon > 0 ? 999.0 : 0.0;
        }
        return (double) setsWon / setsLost;
    }

    /**
     * Calculates Point Ratio: Points Won / Points Lost (handles division by zero).
     */
    public static double calculatePointRatio(int pointsWon, int pointsLost) {
        if (pointsLost == 0) {
            return pointsWon > 0 ? 999.0 : 0.0;
        }
        return (double) pointsWon / pointsLost;
    }
}