package com.matheuscardoso.minefield.model;

import com.matheuscardoso.minefield.exceptions.ExplosionException;

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

    public boolean goalAchieved() {
        return fields.stream().allMatch(Field::goalAchieved);
    }

    public void restart() {
        fields.forEach(Field::restart);
        drawMines();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("  ");
        for (int column = 0; column < columns; column++) {
            stringBuilder.append(" ");
            stringBuilder.append(column);
            stringBuilder.append(" ");
        }
        stringBuilder.append("\n");

        int i = 0;
        for (int row = 0; row < rows; row++) {
            stringBuilder.append(row);
            stringBuilder.append(" ");
            for (int column = 0; column < columns; column++) {
                stringBuilder.append(" ");
                stringBuilder.append(fields.get(i));
                stringBuilder.append(" ");
                i++;
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public void open(Integer row, Integer column) {
        try {
            fields.parallelStream()
                    .filter(field -> field.getRow() == row && field.getColumn() == column)
                    .findFirst()
                    .ifPresent(field -> field.openField());
        } catch (ExplosionException e) {
            fields.forEach(field -> field.setOpen(true));
            throw e;
        }
    }

    public void toggleMarking(Integer row, Integer column) {
        fields.parallelStream()
                .filter(field -> field.getRow() == row && field.getColumn() == column)
                .findFirst()
                .ifPresent(field -> field.changeFlag());
    }
}
