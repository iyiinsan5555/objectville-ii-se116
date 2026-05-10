public abstract class Cell implements Transferable{
    private int x;
    private int y;
    private Point location;

    @Override
    public boolean isTransferable() {
        return true;
    }


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
