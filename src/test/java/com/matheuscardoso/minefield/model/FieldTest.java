package com.matheuscardoso.minefield.model;

import com.matheuscardoso.minefield.exceptions.ExplosionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {
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
        assertTrue(field.fieldIsFlagged());
    }

    @Test
    void testOpenFieldSuccessfully() {
        field.fieldIsMined();
        assertTrue(field.openField());
    }

    @Test
    void testOpenFieldFalse() {
        field.changeFlag();
        assertFalse(field.openField());
    }

    @Test
    void testOpenFieldExplosionException() {
        field.mineTheField();
        assertThrows(ExplosionException.class, () -> {
            field.openField();
        });
    }
}
