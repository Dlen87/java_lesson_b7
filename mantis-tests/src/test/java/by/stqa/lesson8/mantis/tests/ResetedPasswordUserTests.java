package by.stqa.lesson8.mantis.tests;

import by.stqa.lesson8.mantis.appmanager.HttpSession;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ResetedPasswordUserTests extends TestBase {
    private WebDriver wd;

    @Test
    public void testResetPasswordUser() throws Exception {
        goToLoginPage();
      //  String username = "administrator";
       // String locator = "username";
        loginToMantis( "username", "administrator");
        loginToMantis( "password", "secret");
        controlUsers();
        selectUser();
        resetPassword();
    }

    private void resetPassword() {
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
    }

    private void selectUser() {
        wd.findElement(By.linkText("user2")).click();
    }

    private void controlUsers() {
        wd.findElement(By.linkText("Управление пользователями")).click();
    }

    private void loginToMantis(String locator, String key) {
        wd.findElement(By.xpath("//input[@id='" + locator + "']")).click();
        wd.findElement(By.xpath("//input[@id='" + locator + "']")).clear();
        wd.findElement(By.xpath("//input[@id='" + locator + "']")).sendKeys(key);
        wd.findElement(By.xpath("//input[@value='Войти']")).click();
    }

    private void goToLoginPage() {
        wd.get("http://localhost/mantisbt-2.22.0/login_page.php");
    }
}
