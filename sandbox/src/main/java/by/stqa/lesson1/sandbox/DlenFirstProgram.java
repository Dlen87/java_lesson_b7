package by.stqa.lesson1.sandbox;


public class DlenFirstProgram {

    public static void main(String[] args){
        Point p1 = new Point(2,3);
        Point p2 = new Point(8,7);

        System.out.println("Первая точка p1 имеет координаты: x=" + p1.x + " y=" + p1.y);
        System.out.println("Вторая точка p2 имеет координаты: x=" + p2.x + " y=" + p2.y);

        double distance = distance(p1, p2);

        System.out.println("Расстояние между двумя точками равно: " + distance);
        System.out.println("___________________________________________________________________");

        Point p3 = new Point(2, 1 , 3, -3);

        System.out.println("Первая точка имеет координаты: x1=" + p3.x + " y1=" + p3.y);
        System.out.println("Вторая точка имеет координаты: x2=" + p3.x2 + " y2=" + p3.y2);
        System.out.println("Расстояние между двумя точками равно: " + p3.distance());


    }

    public static double distance(Point p1, Point p2){

        double sqrt = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
        return sqrt;
    }
}
