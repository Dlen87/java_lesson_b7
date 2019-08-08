package by.stqa.lesson1.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void distance(){

        Point p1 = new Point(2,3);
        Point p2 = new Point(2,2);

        Assert.assertEquals(p1.distance(p2), 1.0);

        Point p3 = new Point(3,9,7,9);
        assert p3.distance() == 4.0;
    }
}
