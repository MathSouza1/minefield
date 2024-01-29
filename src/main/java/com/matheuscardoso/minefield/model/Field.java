package com.matheuscardoso.minefield.model;

import com.matheuscardoso.minefield.exceptions.ExplosionException;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private final int row;
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
        if (theFieldIsOpen()) {
            flaggedField = !flaggedField;
        }
    }

    boolean openField() {
        if (theFieldIsOpenAndIsFlagged()) {
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

    void mineTheField() {
        minedField = true;
    }

    boolean fieldIsMined() {
        return minedField;
    }
    boolean fieldIsFlagged() {
        return flaggedField;
    }

    private boolean theFieldIsOpenAndIsFlagged() {
        return theFieldIsOpen() && !fieldIsFlagged();
    }

    private boolean theFieldIsOpen() {
        return !openField;
    }

    private boolean isNeighborOfTheSameRowOrColumn() {
        return deltaGeneral == ONE && !diagonal;
    }

    private boolean isNeighborOfTheDiagonal() {
        return deltaGeneral == TWO && diagonal;
    }
}
