package by.stqa.lesson2.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void loginOut() {
        click(By.linkText("Logout"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void gotoHomePage() {
        if (isElementPresent(By.tagName("table"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void gotoGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && (wd.findElement(By.tagName("h1")).getText().equals("Groups"))
                && isElementPresent(By.name("new"))){
            return;
        }
        click(By.linkText("groups"));
    }
}
