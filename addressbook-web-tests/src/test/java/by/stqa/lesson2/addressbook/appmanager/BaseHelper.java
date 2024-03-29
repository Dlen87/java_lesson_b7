package by.stqa.lesson2.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class BaseHelper {
    protected WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd=wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String equalText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(equalText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }
    protected void attach(By locator, File photo) {
        if (photo != null) {
                wd.findElement(locator).sendKeys(photo.getAbsolutePath());
        }
    }

    protected void typeSelect(String bday, By locator) {
        wd.findElement(locator).click();
        new Select(wd.findElement(locator)).selectByVisibleText(bday);
        wd.findElement(By.xpath("//option[@value='" + bday + "']")).click();
    }

    protected boolean isElementPresent(By locator) {
        try{
            wd.findElement(locator);
            return true;
        }
        catch (NoSuchElementException ex){
            return false;
        }

    }
}
