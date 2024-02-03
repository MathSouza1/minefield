package com.matheuscardoso.minefield.observers;

import com.matheuscardoso.minefield.enumerators.FieldEvent;
import com.matheuscardoso.minefield.model.Field;

@FunctionalInterface
public interface FieldObserver {
    void eventHappened(Field field, FieldEvent fieldEvent);
}
