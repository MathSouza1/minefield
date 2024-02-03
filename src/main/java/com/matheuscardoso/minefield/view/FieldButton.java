package com.matheuscardoso.minefield.view;

import com.matheuscardoso.minefield.enumerators.FieldEvent;
import com.matheuscardoso.minefield.model.Field;
import com.matheuscardoso.minefield.observers.FieldObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldButton extends JButton implements FieldObserver, MouseListener {

    private final Color BACKGROUND_DEFAULT = new Color(184, 184, 184);
    private final Color BACKGROUND_FLAGGED = new Color(8, 179, 247);
    private final Color BACKGROUND_EXPLODE = new Color(189, 66, 68);
    private final Color TEXT_GREEN = new Color(0, 100, 0);
    private Field field;

    public FieldButton(Field field) {
        this.field = field;
        setBackground(BACKGROUND_DEFAULT);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(0));
        addMouseListener(this);
        field.saveObserver(this);
    }

    @Override
    public void eventHappened(Field field, FieldEvent fieldEvent) {
        switch (fieldEvent) {
            case OPEN:
                applyOpenStyle();
                break;
            case FLAGGED:
                applyFlaggedStyle();
                break;
            case EXPLODE:
                applyExplodeStyle();
                break;
            default:
                applyDefaultStyle();
        }
        SwingUtilities.invokeLater(() -> {
            repaint();
            validate();
        });
    }

    private void applyDefaultStyle() {
        setBackground(BACKGROUND_DEFAULT);
        setBorder(BorderFactory.createBevelBorder(0));
        setText("");
    }

    private void applyExplodeStyle() {
        setBackground(BACKGROUND_EXPLODE);
        setForeground(Color.WHITE);
        setText("X");
    }

    private void applyFlaggedStyle() {
        setBackground(BACKGROUND_FLAGGED);
        setForeground(Color.BLACK);
        setText("M");
    }

    private void applyOpenStyle() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        if (field.isMined()) {
            setBackground(BACKGROUND_EXPLODE);
            return;
        }
        setBackground(BACKGROUND_DEFAULT);
        switch (field.minesInTheNeighborhood()) {
            case 1:
                setForeground(TEXT_GREEN);
                break;
            case 2:
                setForeground(Color.BLUE);
                break;
            case 3:
                setForeground(Color.YELLOW);
                break;
            case 4:
            case 5:
            case 6:
                setForeground(Color.RED);
                break;
            default:
                setForeground(Color.PINK);
        }
        String valueOfMinesInTheNeighborhood = !field.neighborhoodIsSafe() ? field.minesInTheNeighborhood() + "" : "";
        setText(valueOfMinesInTheNeighborhood);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == 1) {
            field.openField();
        } else {
            field.changeFlag();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
