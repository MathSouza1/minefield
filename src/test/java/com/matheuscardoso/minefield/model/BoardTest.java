package com.matheuscardoso.minefield.model;

import com.matheuscardoso.minefield.exceptions.ExplosionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    void initBoard() {
        board = new Board(3, 3, 2);
    }

    @Test
    void runBoardSuccessfully() {
    }
}
