package com.example.pia_labdismov.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class StandingsCalculatorTest {

    @Test
    public void testCalculateSetRatio() {
        // 9 sets won, 3 sets lost -> 3.0
        assertEquals(3.0, StandingsCalculator.calculateSetRatio(9, 3), 0.001);

        // Zero sets lost
        assertEquals(999.0, StandingsCalculator.calculateSetRatio(5, 0), 0.001);
    }

    @Test
    public void testCalculatePointRatio() {
        // 225 points won, 200 points lost -> 1.125
        assertEquals(1.125, StandingsCalculator.calculatePointRatio(225, 200), 0.001);
    }
}