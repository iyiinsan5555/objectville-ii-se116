public abstract class Cell {
    private int x;
    private int y;
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

    @Override
    public String toString() {
        return "(x" + x + ",y" + y + ")";
    }

    
}
