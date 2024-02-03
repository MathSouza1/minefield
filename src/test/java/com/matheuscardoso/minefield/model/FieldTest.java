package com.matheuscardoso.minefield.model;

import com.matheuscardoso.minefield.observers.FieldObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FieldTest {
    @InjectMocks
    private Field field;

    @BeforeEach
    void initField() {
        field = new Field(3, 3);
    }

    @Test
    void testIsNeighborAtSide() {
        Field neighbor = new Field(3,2);
        boolean result = field.addNeighbor(neighbor);
        assertTrue(result);
    }

    @Test
    void testIsNeighborAtDiagonal() {
        Field neighbor = new Field(2,2);
        boolean result = field.addNeighbor(neighbor);
        assertTrue(result);
    }

    @Test
    void testIsNotNeighbor() {
        Field neighbor = new Field(1, 1);
        boolean result = field.addNeighbor(neighbor);
        assertFalse(result);
    }

    @Test
    void testIsFlagged() {
        field.changeFlag();
        assertTrue(field.isFlagged());
    }

    @Test
    void testOpenFieldSuccessfully() {
        assertTrue(field.openField());
    }

    @Test
    void testOpenFieldExplode() {
        field.mineTheField();
        assertTrue(field.openField());
    }

    @Test
    void testOpenFieldFalse() {
        field.changeFlag();
        assertFalse(field.openField());
    }

    @Test
    void testGoalAchievedSuccessfully() {
        field.setOpen(true);
        assertTrue(field.goalAchieved());
    }

    @Test
    void testMinesInTheNeighborhood() {
        boolean valid = true;
        try {
            field.minesInTheNeighborhood();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testRestart() {
        field.restart();
    }


    @Test
    void changeFlagUnfflaged() {
        boolean valid = true;
        try {
            field.setFlaggedField(true);
            field.changeFlag();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testToStringFlaggedField() {
        boolean valid = true;
        try {
            field.changeFlag();
            field.toString();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testToStringOpenFieldAndMinedField() {
        boolean valid = true;
        try {
            field.setOpen(true);
            field.mineTheField();
            field.toString();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testToStringOpenField() {
        boolean valid = true;
        try {
            field.isMined();
            field.setOpen(true);
            field.toString();
        } catch (Exception e) {
            valid = false;
        }
    }

    @Test
    void testToStringLastReturn() {
        boolean valid = true;
        try {
            field.toString();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testGetter() {
        boolean valid = true;
        try {
            field.getRow();
            field.getColumn();
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testSaveObserver() {
        boolean valid = true;
        try {
            FieldObserver fieldObserver = null;
            field.saveObserver(fieldObserver);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }
    
}
