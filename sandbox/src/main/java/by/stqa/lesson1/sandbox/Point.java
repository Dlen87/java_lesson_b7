package by.stqa.lesson1.sandbox;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }


    public double distance(Point p2){

        double sqrt = Math.sqrt(Math.pow(p2.x - this.x, 2) + Math.pow(p2.y - this.y, 2));
        return sqrt;
    }
}

