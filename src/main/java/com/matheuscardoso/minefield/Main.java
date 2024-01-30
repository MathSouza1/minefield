package com.matheuscardoso.minefield;

import com.matheuscardoso.minefield.model.Board;
import com.matheuscardoso.minefield.view.BoardConsole;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(6,6,3);
        new BoardConsole(board);
    }
}