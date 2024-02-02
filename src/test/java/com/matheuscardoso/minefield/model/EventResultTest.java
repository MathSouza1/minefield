package com.matheuscardoso.minefield.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventResultTest {

    @InjectMocks
    private EventResult eventResult;

    @BeforeEach
    void init() {
        eventResult = new EventResult(true);
    }

    @Test
    void testIsWinsSuccessfully() {
        boolean valid = true;
        try {
            eventResult.wins();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }
}
