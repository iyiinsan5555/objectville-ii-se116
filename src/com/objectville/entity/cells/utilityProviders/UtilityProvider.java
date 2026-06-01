package com.objectville.entity.cells.utilityProviders;

import com.objectville.entity.cells.Cell;

public abstract class UtilityProvider extends Cell {
    private final int capacity = 100;

    public UtilityProvider(int row, int column){
        super(row, column);
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract void refresh();

}