package by.stqa.lesson1.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTest {

    @Test
    public void testArea(){
        int a = 2;
        int square = a * a;
        Assert.assertEquals(square, 3);
    }
}
