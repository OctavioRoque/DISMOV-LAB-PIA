package com.example.pia_labdismov.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class RotationRuleEngineTest {

    @Test
    public void testClockwiseRotation() {
        // Initial positions: [IV=4, III=3, II=2, I=1, VI=6, V=5]
        long[] initial = {4L, 3L, 2L, 1L, 6L, 5L};
        long[] rotated = RotationRuleEngine.rotateClockwise(initial);

        // Expected rotated array: [5, 4, 3, 2, 1, 6]
        assertEquals(5L, rotated[0]); // Pos IV
        assertEquals(4L, rotated[1]); // Pos III
        assertEquals(3L, rotated[2]); // Pos II
        assertEquals(2L, rotated[3]); // Pos I
        assertEquals(1L, rotated[4]); // Pos VI
        assertEquals(6L, rotated[5]); // Pos V
    }

    @Test
    public void testBackRowPositions() {
        assertTrue(RotationRuleEngine.isBackRowPosition(3)); // Pos I
        assertTrue(RotationRuleEngine.isBackRowPosition(4)); // Pos VI
        assertTrue(RotationRuleEngine.isBackRowPosition(5)); // Pos V

        assertFalse(RotationRuleEngine.isBackRowPosition(0)); // Pos IV
        assertFalse(RotationRuleEngine.isBackRowPosition(1)); // Pos III
        assertFalse(RotationRuleEngine.isBackRowPosition(2)); // Pos II
    }
}