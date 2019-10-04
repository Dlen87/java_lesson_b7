package by.stqa.lesson8.mantis.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ResetedPasswordUserTests extends TestBase {
    private WebDriver wd;

    @Test
    public void testResetPasswordUser() throws Exception {
        wd.get("http://localhost/mantisbt-2.22.0/login_page.php");
        wd.findElement(By.xpath("//input[@id='username']")).click();
        wd.findElement(By.xpath("//input[@id='username']")).clear();
        wd.findElement(By.xpath("//input[@id='username']")).sendKeys("administrator");
        wd.findElement(By.xpath("//input[@value='Войти']")).click();
        wd.findElement(By.xpath("//input[@id='password']")).click();
        wd.findElement(By.xpath("//input[@id='password']")).clear();
        wd.findElement(By.xpath("//input[@id='password']")).sendKeys("secret");
        wd.findElement(By.xpath("//input[@value='Войти']")).click();
        wd.findElement(By.linkText("user")).click();
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
        wd.findElement(By.linkText("Продолжить")).click();
        wd.findElement(By.linkText("Управление пользователями")).click();
    }
}
