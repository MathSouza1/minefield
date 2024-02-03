package com.matheuscardoso.minefield.model;

import com.matheuscardoso.minefield.enumerators.FieldEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    @InjectMocks
    private Board board;

    @Mock
    private final Field field = new Field(1,1);

    @BeforeEach
    void initBoard() {
        field.mineTheField();
        field.setFlaggedField(true);
        board = new Board(3, 3, 2);
    }

    @Test
    void testGetters() {
        boolean valid = true;
        try {
            board.getColumns();
            board.getRows();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
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

    @Test
    void testEventHappenedExplode() {
        boolean valid = true;
        try {
            board.eventHappened(field, FieldEvent.EXPLODE);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testEventHappenedNotExplode() {
        boolean valid = true;
        try {
            board.eventHappened(field, FieldEvent.FLAGGED);
            //TODO notifyObservers method may have coverage
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testForEachField() {
        boolean valid = true;
        try {
            board.forEachField(field1 -> {});
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testRegisterObserver() {
        boolean valid = true;
        try {
            board.registerObserver(field1 -> {});
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }
}
