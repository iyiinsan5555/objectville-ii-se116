package com.objectville.entity.cells;

import com.objectville.util.Point;

public abstract class Cell {
    private Point location;


    public Cell(int x, int y){
        location = new Point(x,y);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
