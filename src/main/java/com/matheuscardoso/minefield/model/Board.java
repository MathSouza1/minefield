package com.matheuscardoso.minefield.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Board {
    private int rows;
    private int columns;
    private int mines;
    private final List<Field> fields = new ArrayList<>();

    public Board(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        generateFields();
        associateNeighbors();
        drawMines();
    }

    private void drawMines() {
        long armedMines = 0;
        Predicate<Field> mined = Field::isMined;
        do {
            int randomNumber = (int) (Math.random() * fields.size());
            fields.get(randomNumber).mineTheField();
            armedMines = fields.stream().filter(mined).count();
        } while (armedMines < mines);
    }

    private void associateNeighbors() {
        for(Field firstField: fields) {
            for (Field secondField: fields) {
                firstField.addNeighbor(secondField);
            }
        }
    }

    private void generateFields() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                fields.add(new Field(row, column));
            }
        }
    }
}
