public abstract class Cell {
    private int x;
    private int y;
    Point location;
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
