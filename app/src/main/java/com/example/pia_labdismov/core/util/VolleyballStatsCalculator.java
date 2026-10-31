package com.example.pia_labdismov.core.util;

/**
 * Pure Java statistical calculator for FIVB volleyball metrics.
 * Computes attack efficiency, reception rating, side-out %, and break-point %.
 */
public final class VolleyballStatsCalculator {

    private VolleyballStatsCalculator() {
        // Private constructor
    }

    /**
     * Calculates attack efficiency: (Kills - Errors) / Total Attempts.
     * 
     * @val kills Successful attacks resulting in points
     * @val errors Attack errors (out, net, blocked out)
     * @val attempts Total attack attempts
     */
    public static double calculateAttackEfficiency(int kills, int errors, int attempts) {
        if (attempts <= 0) return 0.0;
        return (double) (kills - errors) / attempts;
    }

    /**
     * Calculates reception rating scale (0 to 3 or 4 scale average).
     * 
     * @param sumGrades Sum of reception grades (0, 1, 2, 3)
     * @param totalReceptions Total number of receptions
     */
    public static double calculateReceptionAverage(int sumGrades, int totalReceptions) {
        if (totalReceptions <= 0) return 0.0;
        return (double) sumGrades / totalReceptions;
    }

    /**
     * Calculates Side-Out percentage: (Side-out wins / Total reception opportunities) * 100.
     */
    public static double calculateSideOutPercentage(int sideOutWins, int receptionOpportunities) {
        if (receptionOpportunities <= 0) return 0.0;
        return ((double) sideOutWins / receptionOpportunities) * 100.0;
    }

    /**
     * Calculates Break-Point percentage: (Break-point wins / Total serve opportunities) * 100.
     */
    public static double calculateBreakPointPercentage(int breakPointWins, int serveOpportunities) {
        if (serveOpportunities <= 0) return 0.0;
        return ((double) breakPointWins / serveOpportunities) * 100.0;
    }
}