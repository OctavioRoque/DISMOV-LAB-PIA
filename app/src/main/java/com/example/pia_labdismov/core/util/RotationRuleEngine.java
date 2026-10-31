package com.example.pia_labdismov.core.util;

/**
 * Pure Java rule engine for Volleyball rotations, positions (I to VI), 
 * and positional fault validation according to FIVB rules.
 */
public final class RotationRuleEngine {

    private RotationRuleEngine() {
        // Private constructor
    }

    /**
     * Performs a clockwise rotation on the 6 court positions.
     * Positions array format: [Pos IV, Pos III, Pos II, Pos I, Pos VI, Pos V]
     * When rotating clockwise:
     * - Pos II -> Pos I
     * - Pos I -> Pos VI
     * - Pos VI -> Pos V
     * - Pos V -> Pos IV
     * - Pos IV -> Pos III
     * - Pos III -> Pos II
     * 
     * @param currentPositions Array of 6 court positions [IV, III, II, I, VI, V]
     * @return Rotated array
     */
    public static long[] rotateClockwise(long[] currentPositions) {
        if (currentPositions == null || currentPositions.length != 6) {
            throw new IllegalArgumentException("Rotations require exactly 6 court positions");
        }

        long iv = currentPositions[0];
        long iii = currentPositions[1];
        long ii = currentPositions[2];
        long i = currentPositions[3];
        long vi = currentPositions[4];
        long v = currentPositions[5];

        return new long[]{
            v,   // New Pos IV comes from Pos V
            iv,  // New Pos III comes from Pos IV
            iii, // New Pos II comes from Pos III
            ii,  // New Pos I comes from Pos II
            i,   // New Pos VI comes from Pos I
            vi   // New Pos V comes from Pos VI
        };
    }

    public static boolean isBackRowPosition(int positionIndex) {
        return positionIndex >= 3 && positionIndex <= 5;
    }
}