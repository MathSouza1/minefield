package com.matheuscardoso.minefield.exceptions;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExplosionExceptionTest {

    @InjectMocks
    private ExplosionException exception;

    @Test
    void testExplosionExceptionSuccessfully() {
        boolean valid = true;
        try {
            exception = new ExplosionException();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }
}
