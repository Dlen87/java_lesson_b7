package by.stqa.lesson2.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd=wd;
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void loginOut() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void gotoHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    public void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }
}
