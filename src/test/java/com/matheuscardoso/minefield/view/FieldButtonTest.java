package com.matheuscardoso.minefield.view;

import com.matheuscardoso.minefield.enumerators.FieldEvent;
import com.matheuscardoso.minefield.model.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FieldButtonTest {

    @InjectMocks
    private FieldButton fieldButton;

    @Mock
    private MouseEvent mouseEvent;

    @Mock
    private Component component;

    private Field field = new Field(2, 2);

    @BeforeEach
    void init() {
        fieldButton = new FieldButton(field);
    }

    @Test
    void testEventHappenedOpenAndApplyOpenStyleDefault() {
        boolean valid = true;
        try {
            FieldEvent fieldEvent = FieldEvent.OPEN;
            fieldButton.eventHappened(field, fieldEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testEventHappenedFlaggedAndApplyFlaggedStyle() {
        boolean valid = true;
        try {
            FieldEvent fieldEvent = FieldEvent.FLAGGED;
            fieldButton.eventHappened(field, fieldEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testEventHappenedExplodeAndApplyExplodeStyle() {
        boolean valid = true;
        try {
            FieldEvent fieldEvent = FieldEvent.EXPLODE;
            fieldButton.eventHappened(field, fieldEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testEventHappenedUnflaggedAndApplyDefaultStyle() {
        boolean valid = true;
        try {
            FieldEvent fieldEvent = FieldEvent.UNFLAGGED;
            fieldButton.eventHappened(field, fieldEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testApplyOpenStyleIsMinedTrue() {
        boolean valid = true;
        try {
            FieldEvent fieldEvent = FieldEvent.OPEN;
            field.mineTheField();
            fieldButton.eventHappened(field, fieldEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testApplyOpenStyleCaseOne() {
        boolean valid = true;
        try {
            FieldEvent fieldEvent = FieldEvent.OPEN;
            Field field1 = new Field(1,1);
            field1.mineTheField();
            field.addNeighbor(field1);
            fieldButton.eventHappened(field, fieldEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testApplyOpenStyleCaseTwo() {
        boolean valid = true;
        try {
            FieldEvent fieldEvent = FieldEvent.OPEN;
            Field field1 = new Field(1,1);
            field1.mineTheField();
            Field field2 = new Field(1,2);
            field2.mineTheField();
            field.addNeighbor(field1);
            field.addNeighbor(field2);
            fieldButton.eventHappened(field, fieldEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testApplyOpenStyleCaseThree() {
        boolean valid = true;
        try {
            FieldEvent fieldEvent = FieldEvent.OPEN;
            Field field1 = new Field(1,1);
            field1.mineTheField();
            Field field2 = new Field(1,2);
            field2.mineTheField();
            Field field3 = new Field(1,3);
            field3.mineTheField();
            field.addNeighbor(field1);
            field.addNeighbor(field2);
            field.addNeighbor(field3);
            fieldButton.eventHappened(field, fieldEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testApplyOpenStyleCaseFour() {
        boolean valid = true;
        try {
            FieldEvent fieldEvent = FieldEvent.OPEN;
            Field field1 = new Field(1,1);
            field1.mineTheField();
            Field field2 = new Field(1,2);
            field2.mineTheField();
            Field field3 = new Field(1,3);
            field3.mineTheField();
            Field field4 = new Field(2,1);
            field4.mineTheField();
            field.addNeighbor(field1);
            field.addNeighbor(field2);
            field.addNeighbor(field3);
            field.addNeighbor(field4);
            fieldButton.eventHappened(field, fieldEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testMouseClicked() {
        fieldButton.mouseClicked(mouseEvent);
    }

    @Test
    void testMousePressedButtonDifferentOne() {
        boolean valid = true;
        try {
            component = new Component() {
                @Override
                public String getName() {
                    return super.getName();
                }

                @Override
                public void setName(String name) {
                    super.setName(name);
                }

                @Override
                public Container getParent() {
                    return super.getParent();
                }

                @Override
                public synchronized void setDropTarget(DropTarget dt) {
                    super.setDropTarget(dt);
                }

                @Override
                public synchronized DropTarget getDropTarget() {
                    return super.getDropTarget();
                }

                @Override
                public GraphicsConfiguration getGraphicsConfiguration() {
                    return super.getGraphicsConfiguration();
                }

                @Override
                public Toolkit getToolkit() {
                    return super.getToolkit();
                }

                @Override
                public boolean isValid() {
                    return super.isValid();
                }

                @Override
                public boolean isDisplayable() {
                    return super.isDisplayable();
                }

                @Override
                public boolean isVisible() {
                    return super.isVisible();
                }

                @Override
                public Point getMousePosition() throws HeadlessException {
                    return super.getMousePosition();
                }

                @Override
                public boolean isShowing() {
                    return super.isShowing();
                }

                @Override
                public boolean isEnabled() {
                    return super.isEnabled();
                }

                @Override
                public void setEnabled(boolean b) {
                    super.setEnabled(b);
                }
            };
            mouseEvent = new MouseEvent(component, 1, 1L, InputEvent.ALT_DOWN_MASK, 1, 1, 1, true, 1);
            fieldButton.mousePressed(mouseEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testMousePressedButtonEqualsOne() {
        boolean valid = true;
        try {
            component = new Component() {
                @Override
                public String getName() {
                    return super.getName();
                }

                @Override
                public void setName(String name) {
                    super.setName(name);
                }

                @Override
                public Container getParent() {
                    return super.getParent();
                }

                @Override
                public synchronized void setDropTarget(DropTarget dt) {
                    super.setDropTarget(dt);
                }

                @Override
                public synchronized DropTarget getDropTarget() {
                    return super.getDropTarget();
                }

                @Override
                public GraphicsConfiguration getGraphicsConfiguration() {
                    return super.getGraphicsConfiguration();
                }

                @Override
                public Toolkit getToolkit() {
                    return super.getToolkit();
                }

                @Override
                public boolean isValid() {
                    return super.isValid();
                }

                @Override
                public boolean isDisplayable() {
                    return super.isDisplayable();
                }

                @Override
                public boolean isVisible() {
                    return super.isVisible();
                }

                @Override
                public Point getMousePosition() throws HeadlessException {
                    return super.getMousePosition();
                }

                @Override
                public boolean isShowing() {
                    return super.isShowing();
                }

                @Override
                public boolean isEnabled() {
                    return super.isEnabled();
                }

                @Override
                public void setEnabled(boolean b) {
                    super.setEnabled(b);
                }
            };
            mouseEvent = new MouseEvent(component, 1, 1L, InputEvent.ALT_DOWN_MASK, 1, 1, 1, true);
            fieldButton.mousePressed(mouseEvent);
        } catch (Exception e) {
            valid = false;
        }
        assertTrue(valid);
    }

    @Test
    void testMouseReleased() {
        fieldButton.mouseReleased(mouseEvent);
    }

    @Test
    void testMouseEntered() {
        fieldButton.mouseEntered(mouseEvent);
    }

    @Test
    void testMouseExited() {
        fieldButton.mouseExited(mouseEvent);
    }
}
