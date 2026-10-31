package com.example.pia_labdismov.core.util;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Java Unit Test for PlayerValidationUtil (Java-Kotlin interop verification).
 */
public class PlayerValidationUtilTest {

    @Test
    public void testIsValidJerseyNumber() {
        assertTrue(PlayerValidationUtil.isValidJerseyNumber(1));
        assertTrue(PlayerValidationUtil.isValidJerseyNumber(7));
        assertTrue(PlayerValidationUtil.isValidJerseyNumber(99));

        assertFalse(PlayerValidationUtil.isValidJerseyNumber(0));
        assertFalse(PlayerValidationUtil.isValidJerseyNumber(-1));
        assertFalse(PlayerValidationUtil.isValidJerseyNumber(100));
    }

    @Test
    public void testIsJerseyNumberUnique() {
        List<Integer> existingNumbers = Arrays.asList(7, 10, 18);
        List<Long> playerIds = Arrays.asList(101L, 102L, 103L);

        // Jersey #5 is not taken -> Unique
        assertTrue(PlayerValidationUtil.isJerseyNumberUnique(5, existingNumbers, 0L, playerIds));

        // Jersey #7 is taken by player 101L -> Not unique for new player (id 0)
        assertFalse(PlayerValidationUtil.isJerseyNumberUnique(7, existingNumbers, 0L, playerIds));

        // Jersey #7 is taken by player 101L -> Unique when updating player 101L
        assertTrue(PlayerValidationUtil.isJerseyNumberUnique(7, existingNumbers, 101L, playerIds));
    }

    @Test
    public void testIsValidPlayerName() {
        assertTrue(PlayerValidationUtil.isValidPlayerName("Ana"));
        assertTrue(PlayerValidationUtil.isValidPlayerName("Carlos Rodriguez"));

        assertFalse(PlayerValidationUtil.isValidPlayerName(null));
        assertFalse(PlayerValidationUtil.isValidPlayerName(""));
        assertFalse(PlayerValidationUtil.isValidPlayerName(" A "));
    }
}