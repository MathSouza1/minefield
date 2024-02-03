package com.matheuscardoso.minefield;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainScreenTest {

    @InjectMocks
    private MainScreen mainScreen;

    @Test
    void testMainScreenSuccessfully() {
        boolean valid = true;
        try {
            MainScreen.main(new String[] {});
            mainScreen = new MainScreen();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }
}
