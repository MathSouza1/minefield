package com.matheuscardoso.minefield.model;

import com.matheuscardoso.minefield.enumerators.FieldEvent;
import com.matheuscardoso.minefield.observers.FieldObserver;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Board implements FieldObserver {

    @Getter
    private int rows;

    @Getter
    private int columns;
    private int mines;
    private final List<Field> fields = new ArrayList<>();
    private final List<Consumer<EventResult>> observers = new ArrayList<>();

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
                Field field = new Field(row, column);
                field.saveObserver(this);
                fields.add(field);
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
        fields.parallelStream()
                .filter(field -> field.getRow() == row && field.getColumn() == column)
                .findFirst()
                .ifPresent(Field::openField);
    }

    public void toggleMarking(Integer row, Integer column) {
        fields.parallelStream()
                .filter(field -> field.getRow() == row && field.getColumn() == column)
                .findFirst()
                .ifPresent(Field::changeFlag);
    }

    public void forEachField(Consumer<Field> function) {
        fields.forEach(function);
    }

    public void registerObserver(Consumer<EventResult> observer) {
        observers.add(observer);
    }

    public void notifyObservers(boolean result) {
        observers.forEach(observer -> observer.accept(new EventResult(result)));
    }

    @Override
    public void eventHappened(Field field, FieldEvent fieldEvent) {
        if (fieldEvent == FieldEvent.EXPLODE) {
            showMines();
            notifyObservers(false);
        } else if (goalAchieved()) {
            notifyObservers(true);
        }
    }

    private void showMines() {
        fields.stream()
                .filter(Field::isMined)
                .filter(field -> !field.isFlagged())
                .forEach(field -> field.setOpen(true));
    }
}
