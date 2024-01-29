package com.matheuscardoso.minefield.model;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private final int row;
    private final int column;
    boolean differentLine, differentColumn, diagonal;
    int deltaLine, deltaColumn, deltaGeneral, mines;
    private boolean open = false;
    private boolean mined = false;
    private boolean flagged = false;
    private List<Field> neighbors = new ArrayList<>();

    public Field(int line, int column) {
        this.row = line;
        this.column = column;
    }

    public boolean addNeighbor(Field neighbor) {
        differentLine = row != neighbor.row;
        differentColumn = column != neighbor.column;
        diagonal = differentLine && differentColumn;
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

    private boolean isNeighborOfTheSameRowOrColumn() {
        return deltaGeneral == ONE && !diagonal;
    }

    private boolean isNeighborOfTheDiagonal() {
        return deltaGeneral == TWO && diagonal;
    }
}
