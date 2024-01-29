package com.matheuscardoso.minefield;

import com.matheuscardoso.minefield.model.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
