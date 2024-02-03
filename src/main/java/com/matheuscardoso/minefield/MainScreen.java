package com.matheuscardoso.minefield;

import com.matheuscardoso.minefield.model.Board;
import com.matheuscardoso.minefield.view.BoardPanel;

import javax.swing.*;

public class MainScreen extends JFrame {

    private static final int NUMBER_OF_ROWS = 16;
    private static final int NUMBER_OF_COLUMNS = 30;
    private static final int NUMBER_OF_MINES = 50;

    private static final int BOARD_WIDTH = 690;
    private static final int BOARD_HEIGHT = 438;

    public MainScreen() {
        Board board = new Board(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, NUMBER_OF_MINES);
        add(new BoardPanel(board));
        setTitle("Minefield");
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}