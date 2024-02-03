package com.matheuscardoso.minefield.model;

import com.matheuscardoso.minefield.enumerators.FieldEvent;
import com.matheuscardoso.minefield.observers.FieldObserver;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private static final int ONE = 1;
    private static final int TWO = 2;
    @Getter
    private int row;
    @Getter
    private int column;
    boolean differentRow, differentColumn, diagonal;
    int deltaLine;
    int deltaColumn;
    int deltaGeneral;
    private boolean openField = false;
    private boolean minedField = false;
    @Setter
    private boolean flaggedField = false;
    private List<Field> neighbors = new ArrayList<>();
    private List<FieldObserver> observers = new ArrayList<>();

    public Field(int line, int column) {
        this.row = line;
        this.column = column;
    }

    public void saveObserver(FieldObserver fieldObserver) {
        observers.add(fieldObserver);
    }

    private void notifyObservers(FieldEvent fieldEvent) {
        observers.forEach(observer -> observer.eventHappened(this, fieldEvent));
    }

    public boolean addNeighbor(Field neighbor) {
        differentRow = row != neighbor.row;
        differentColumn = column != neighbor.column;
        diagonal = differentRow && differentColumn;
        deltaLine = Math.abs(row - neighbor.row);
        deltaColumn = Math.abs(column - neighbor.column);
        deltaGeneral = deltaColumn + deltaLine;

        if (isNeighborOfTheSameRowOrColumn()) {
            neighbors.add(neighbor);
            return true;
        } else if (isNeighborOfTheDiagonal()) {
            neighbors.add(neighbor);
            return true;
        }
        return false;
    }

    public void changeFlag() {
        if (isClosed()) {
            flaggedField = !flaggedField;
            if (flaggedField) {
                notifyObservers(FieldEvent.FLAGGED);
            } else {
                notifyObservers(FieldEvent.UNFLAGGED);
            }
        }
    }

    public boolean openField() {
        if (isOpenAndIsFlagged()) {
            if (minedField) {
                notifyObservers(FieldEvent.EXPLODE);
                return true;
            }
            setOpen(true);
            if (neighborhoodIsSafe()) {
                neighbors.forEach(Field::openField);
            }
            return true;
        }
        return false;
    }

    public boolean neighborhoodIsSafe() {
        return neighbors.stream().noneMatch(neighbor -> neighbor.minedField);
    }

    boolean goalAchieved() {
        boolean unraveled = !minedField && openField;
        boolean protect = minedField && flaggedField;
        return unraveled || protect;
    }

    public int minesInTheNeighborhood() {
        return (int) neighbors.stream().filter(field -> field.minedField).count();
    }

    void restart() {
        openField = false;
        minedField = false;
        flaggedField = false;
        notifyObservers(FieldEvent.RESTART);
    }

    public void mineTheField() {
        minedField = true;
    }

    public boolean isMined() {
        return minedField;
    }

    boolean isFlagged() {
        return flaggedField;
    }

    void setOpen(boolean openField) {
        this.openField = openField;
        if (openField) {
            notifyObservers(FieldEvent.OPEN);
        }
    }

    boolean isOpen() {
        return openField;
    }

    boolean isClosed() {
        return !isOpen();
    }

    private boolean isOpenAndIsFlagged() {
        return isClosed() && !isFlagged();
    }

    private boolean isNeighborOfTheSameRowOrColumn() {
        return deltaGeneral == ONE && !diagonal;
    }

    private boolean isNeighborOfTheDiagonal() {
        return deltaGeneral == TWO && diagonal;
    }
}
