package com.example.pia_labdismov.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Java-based FIVB Score Rules Engine.
 */
public class FivbScoreRulesEngineTest {

    @Test
    public void testCheckSetWinnerNormal() {
        // Ongoing set (24-24)
        assertEquals(0, FivbScoreRulesEngine.checkSetWinner(24, 24, 25, true));

        // Team A wins 25-23
        assertEquals(1, FivbScoreRulesEngine.checkSetWinner(25, 23, 25, true));

        // Deuce win (26-24)
        assertEquals(1, FivbScoreRulesEngine.checkSetWinner(26, 24, 25, true));

        // Deuce ongoing (26-25)
        assertEquals(0, FivbScoreRulesEngine.checkSetWinner(26, 25, 25, true));

        // Deuce win team B (25-27)
        assertEquals(2, FivbScoreRulesEngine.checkSetWinner(25, 27, 25, true));
    }

    @Test
    public void testCheckSetWinnerDecisiveSet() {
        // Set 5 (15 points) -> Team A wins 15-13
        assertEquals(1, FivbScoreRulesEngine.checkSetWinner(15, 13, 15, true));

        // Set 5 deuce -> 16-14
        assertEquals(2, FivbScoreRulesEngine.checkSetWinner(14, 16, 15, true));
    }

    @Test
    public void testCalculateStandingPoints() {
        // 3-0 win -> [3, 0]
        int[] pts1 = FivbScoreRulesEngine.calculateStandingPoints(3, 0);
        assertEquals(3, pts1[0]);
        assertEquals(0, pts1[1]);

        // 3-2 win -> [2, 1]
        int[] pts2 = FivbScoreRulesEngine.calculateStandingPoints(3, 2);
        assertEquals(2, pts2[0]);
        assertEquals(1, pts2[1]);
    }
}