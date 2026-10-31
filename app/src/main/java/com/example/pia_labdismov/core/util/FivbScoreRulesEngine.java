package com.example.pia_labdismov.core.util;

/**
 * Pure Java rule engine for FIVB Volleyball scoring and match rules.
 * Demonstrates Java-Kotlin Interoperability and encapsulates critical business rules.
 */
public final class FivbScoreRulesEngine {

    private FivbScoreRulesEngine() {
        // Private constructor
    }

    /**
     * Checks if a set has been won according to FIVB rules.
     * Sets 1-4: typically 25 points, win by at least 2 points.
     * Set 5: typically 15 points, win by at least 2 points.
     * Custom target points and limits are supported.
     *
     * @param scoreA       Points of team A
     * @param scoreB       Points of team B
     * @param targetPoints Target points for the set (e.g., 25 or 15)
     * @param mustLeadBy2  Whether a 2-point difference is required
     * @return 1 if team A won the set, 2 if team B won the set, 0 if set is still ongoing.
     */
    public static int checkSetWinner(int scoreA, int scoreB, int targetPoints, boolean mustLeadBy2) {
        if (scoreA < targetPoints && scoreB < targetPoints) {
            return 0;
        }

        int diff = Math.abs(scoreA - scoreB);

        if (mustLeadBy2) {
            if (scoreA >= targetPoints && diff >= 2) {
                return scoreA > scoreB ? 1 : 2;
            }
            if (scoreB >= targetPoints && diff >= 2) {
                return scoreB > scoreA ? 2 : 1;
            }
        } else {
            if (scoreA >= targetPoints) return 1;
            if (scoreB >= targetPoints) return 2;
        }

        return 0;
    }

    /**
     * Checks if a team has won the match.
     * Best of 5 sets (first to win 3 sets) or Best of 3 sets (first to win 2 sets).
     *
     * @param setsWonA      Sets won by team A
     * @param setsWonB      Sets won by team B
     * @param setsToWin     Sets required to win match (3 for best of 5, 2 for best of 3)
     * @return 1 if team A won match, 2 if team B won match, 0 if match ongoing.
     */
    public static int checkMatchWinner(int setsWonA, int setsWonB, int setsToWin) {
        if (setsWonA >= setsToWin) return 1;
        if (setsWonB >= setsToWin) return 2;
        return 0;
    }

    /**
     * Checks if teams should switch sides in the 5th (decisive) set when leading team reaches 8 points.
     */
    public static boolean shouldSwitchSidesInDecisiveSet(int setNumber, int totalSets, int scoreA, int scoreB) {
        if (setNumber == totalSets) {
            return (scoreA == 8 || scoreB == 8) && (scoreA + scoreB == 8);
        }
        return false;
    }

    /**
     * Calculates league ranking points according to FIVB standard:
     * 3-0 or 3-1 win: Winner = 3 pts, Loser = 0 pts.
     * 3-2 win: Winner = 2 pts, Loser = 1 pt.
     */
    public static int[] calculateStandingPoints(int setsWonWinner, int setsWonLoser) {
        int winnerPoints;
        int loserPoints;

        if (setsWonWinner == 3 && setsWonLoser <= 1) {
            winnerPoints = 3;
            loserPoints = 0;
        } else if (setsWonWinner == 3 && setsWonLoser == 2) {
            winnerPoints = 2;
            loserPoints = 1;
        } else {
            // For best of 3
            if (setsWonWinner == 2 && setsWonLoser == 0) {
                winnerPoints = 3;
                loserPoints = 0;
            } else if (setsWonWinner == 2 && setsWonLoser == 1) {
                winnerPoints = 2;
                loserPoints = 1;
            } else {
                winnerPoints = 3;
                loserPoints = 0;
            }
        }
        return new int[]{winnerPoints, loserPoints};
    }
}