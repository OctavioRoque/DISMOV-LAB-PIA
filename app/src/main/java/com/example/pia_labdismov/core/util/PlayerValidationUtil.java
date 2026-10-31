package com.example.pia_labdismov.core.util;

import java.util.List;

/**
 * Utility class written in pure Java to demonstrate Java-Kotlin Interoperability.
 * Validates player data according to FIVB rules and application constraints.
 */
public final class PlayerValidationUtil {

    public static final int MIN_JERSEY_NUMBER = 1;
    public static final int MAX_JERSEY_NUMBER = 99;
    public static final double MIN_HEIGHT_CM = 100.0;
    public static final double MAX_HEIGHT_CM = 250.0;

    private PlayerValidationUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Checks if a jersey number is within legal FIVB range (1 to 99).
     */
    public static boolean isValidJerseyNumber(int jerseyNumber) {
        return jerseyNumber >= MIN_JERSEY_NUMBER && jerseyNumber <= MAX_JERSEY_NUMBER;
    }

    /**
     * Checks if a jersey number is unique among existing jersey numbers in the team.
     *
     * @param targetJersey  The jersey number being added/edited.
     * @param existingNumbers List of existing jersey numbers in the team.
     * @param currentPlayerId ID of current player (0 for new players).
     * @param playerIds      List of player IDs corresponding to existingNumbers.
     * @return true if jersey number is available, false otherwise.
     */
    public static boolean isJerseyNumberUnique(int targetJersey, List<Integer> existingNumbers, 
                                               long currentPlayerId, List<Long> playerIds) {
        if (existingNumbers == null || playerIds == null || existingNumbers.size() != playerIds.size()) {
            return true;
        }

        for (int i = 0; i < existingNumbers.size(); i++) {
            if (existingNumbers.get(i) == targetJersey && playerIds.get(i) != currentPlayerId) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates player name.
     */
    public static boolean isValidPlayerName(String name) {
        return name != null && name.trim().length() >= 2;
    }

    /**
     * Validates height.
     */
    public static boolean isValidHeight(double heightCm) {
        return heightCm == 0.0 || (heightCm >= MIN_HEIGHT_CM && heightCm <= MAX_HEIGHT_CM);
    }
}