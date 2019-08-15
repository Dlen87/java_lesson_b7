package by.stqa.lesson2.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BaseHelper {
    protected WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd=wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    protected void typeSelect(String bday, By locator) {
        wd.findElement(locator).click();
        new Select(wd.findElement(locator)).selectByVisibleText(bday);
        wd.findElement(By.xpath("//option[@value='" + bday + "']")).click();
    }
}
