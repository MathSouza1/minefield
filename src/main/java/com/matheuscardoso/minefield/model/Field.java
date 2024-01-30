package com.matheuscardoso.minefield.model;

import com.matheuscardoso.minefield.exceptions.ExplosionException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private static final int ONE = 1;
    private static final int TWO = 2;
    @Getter
    private final int row;
    @Getter
    private final int column;
    boolean differentRow, differentColumn, diagonal;
    int deltaLine, deltaColumn, deltaGeneral, mines;
    private boolean openField = false;
    private boolean minedField = false;
    private boolean flaggedField = false;
    private List<Field> neighbors = new ArrayList<>();

    public Field(int line, int column) {
        this.row = line;
        this.column = column;
    }

    boolean addNeighbor(Field neighbor) {
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

    void changeFlag() {
        if (isClosed()) {
            flaggedField = !flaggedField;
        }
    }

    boolean openField() {
        if (isOpenAndIsFlagged()) {
            openField = true;
            if (minedField) {
                throw new ExplosionException();
            }
            if (neighborhoodIsSafe()) {
                neighbors.forEach(Field::openField);
            }
            return true;
        }
        return false;
    }

    private boolean neighborhoodIsSafe() {
        return neighbors.stream().noneMatch(neighbor -> neighbor.minedField);
    }

    boolean goalAchieved() {
        boolean unraveled = !minedField && openField;
        boolean protect = minedField && flaggedField;
        return unraveled || protect;
    }

    long minesInTheNeighborhood() {
        return neighbors.stream().filter(field -> field.minedField).count();
    }

    void restart() {
        openField = false;
        minedField = false;
        flaggedField = false;
    }

    public String toString() {
        if (flaggedField) {
            return "x";
        } else if (openField && minedField) {
            return "*";
        } else if (openField && minesInTheNeighborhood() > 0) {
            return Long.toString(minesInTheNeighborhood());
        } else if (openField) {
            return " ";
        }
        return "?";
    }

    void mineTheField() {
        minedField = true;
    }

    boolean isMined() {
        return minedField;
    }

    boolean isFlagged() {
        return flaggedField;
    }

    void setOpen(boolean openField) {
        this.openField = openField;
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
