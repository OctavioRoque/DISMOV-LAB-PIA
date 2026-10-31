package com.example.pia_labdismov.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class VolleyballStatsCalculatorTest {

    @Test
    public void testCalculateAttackEfficiency() {
        // 10 kills, 2 errors, 20 attempts -> (10 - 2) / 20 = 8 / 20 = 0.40
        double efficiency = VolleyballStatsCalculator.calculateAttackEfficiency(10, 2, 20);
        assertEquals(0.40, efficiency, 0.001);

        // Zero attempts
        assertEquals(0.0, VolleyballStatsCalculator.calculateAttackEfficiency(5, 1, 0), 0.001);
    }

    @Test
    public void testCalculateSideOutPercentage() {
        // 15 side-out wins out of 20 receptions -> 75.0%
        double so = VolleyballStatsCalculator.calculateSideOutPercentage(15, 20);
        assertEquals(75.0, so, 0.001);
    }
}