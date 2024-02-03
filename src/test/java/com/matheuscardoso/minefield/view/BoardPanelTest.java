package com.matheuscardoso.minefield.view;

import com.matheuscardoso.minefield.model.Board;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardPanelTest {

    @InjectMocks
    private BoardPanel boardPanel;

    @Mock
    private final Board board = new Board(1,1,1);

    @Test
    void testBoardPanelSuccessfully() {
        boolean valid = true;
        try {
            boardPanel = new BoardPanel(board);
            SwingUtilities.invokeAndWait(() -> {
            });
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }
}
