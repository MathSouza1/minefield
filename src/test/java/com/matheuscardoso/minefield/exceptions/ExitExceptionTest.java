package com.matheuscardoso.minefield.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitExceptionTest {

    @InjectMocks
    private ExitException exception;

    @Test
    void testExitExceptionSuccessfully() {
        boolean valid = true;
        try {
            exception = new ExitException();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }
}
