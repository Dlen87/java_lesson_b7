package by.stqa.lesson8.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetHelper extends HelperBase {
    public ResetHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword() {
        click(By.xpath("//input[@value='Сбросить пароль']"));
    }

    public void selectUser(String user) {
        click(By.linkText(user));
    }

    public void controlUsers() {
        click(By.linkText("Управление пользователями"));
    }

    public void loginToMantis(String locator, String key) {
        click(By.xpath("//input[@id='" + locator + "']"));
        wd.findElement(By.xpath("//input[@id='" + locator + "']")).clear();
        wd.findElement(By.xpath("//input[@id='" + locator + "']")).sendKeys(key);
        click(By.xpath("//input[@value='Войти']"));
    }

    public void goToLoginPage() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }

    public void password(String confirmationLins, String password, String user) {
        wd.get(confirmationLins);
        type(By.name("realname"), user);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
