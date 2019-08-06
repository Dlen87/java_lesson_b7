package by.stqa.lesson1.sandbox;

public class Point {
    public int x;
    public int y;
    public int x2;
    public int y2;

    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Point(int x1, int y1, int x2, int y2){
        this.x=x1;
        this.y=y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double distance(){

        double sqrt = Math.sqrt(Math.pow(this.x2 - this.x, 2) + Math.pow(this.y2 - this.y, 2));
        return sqrt;
    }
}

