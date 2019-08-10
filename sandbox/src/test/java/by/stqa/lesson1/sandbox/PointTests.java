package by.stqa.lesson1.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public  void testPoint() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 1);

        Assert.assertEquals(p1.distance(p2), 1.0);
    }

}
