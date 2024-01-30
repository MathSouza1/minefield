package com.matheuscardoso.minefield.model;

import com.matheuscardoso.minefield.exceptions.ExplosionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    @InjectMocks
    private Board board;

    @Mock
    private Field field;

    @BeforeEach
    void initBoard() {
        board = new Board(3, 3, 2);
    }

    @Test
    void testGoalAchieved() {
        boolean valid = true;
        try {
            board.goalAchieved();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testRestart() {
        boolean valid = true;
        try {
            board.restart();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testToString() {
        boolean valid = true;
        try {
            board.toString();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testOpenSuccessfully() {
        boolean valid = true;
        try {
            board.open(3,3);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testToggleMarking() {
        boolean valid = true;
        try {
            board.toggleMarking(3,3);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }
}
