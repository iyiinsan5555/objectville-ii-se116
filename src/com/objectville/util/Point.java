package com.objectville.util;

import java.util.Objects;

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

        return Math.sqrt(distanceY*distanceY + distanceX*distanceX);
    }

    @Override
    public String toString() {
        return "("+ x + ", " + y + ")";
    }

    //HashMap uses this to distinguish objects of com.objectville.util.Point class
    //Reference: https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-
    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Point)) {
            return false; //the object is not an instance of com.objectville.util.Point
        }
        else if (this == object) {
            return true; //they point to the same mem. location (same reference)
        }

        Point otherPoint = (Point) object;
        return x == otherPoint.getX() && y == otherPoint.getY(); //we are comparing the points x & y fields to conclude that they are same or not
    }

    //This is a hashing function to help HashMap to distinguish points
    //References:
    //https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--
    //https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html#hash-java.lang.Object...-
    //Check this video to understand comprehensively: https://www.youtube.com/watch?v=FsfRsGFHuv4
    @Override
    public int hashCode() {
        return Objects.hash(x, y); //we are letting java generate hash code.
    }
}
