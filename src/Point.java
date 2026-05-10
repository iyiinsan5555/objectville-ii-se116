public class Point {
    private int x;
    private int y;


    //getter setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //gives the distance to another point
    public double distanceTo(Point other){
        int distanceX = other.getX() - this.x;
        int distanceY = other.getY() - this.y;
        int totalDist = (int) Math.sqrt(distanceY*distanceY + distanceX*distanceX);
        return totalDist;
    }
    //in case we need it...
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    //needed to make sure hashmap sees this class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;
        return x == point.x && y == point.y;
    }
    // same reason as equals'
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}