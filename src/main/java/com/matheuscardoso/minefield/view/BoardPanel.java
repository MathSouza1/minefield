package com.matheuscardoso.minefield.view;

import com.matheuscardoso.minefield.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    public BoardPanel(Board board) {
        setLayout(new GridLayout(board.getRows(), board.getColumns()));
        board.forEachField(field -> add(new FieldButton(field)));
        board.registerObserver(event -> {
            SwingUtilities.invokeLater(() -> {
                if (event.wins()) {
                    JOptionPane.showMessageDialog(this, "You Win :)");
                } else {
                    JOptionPane.showMessageDialog(this, "You Lost :(");
                }
                board.restart();
            });
        });
    }
}
