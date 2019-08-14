package by.stqa.lesson2.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactDeleteTests {
  private WebDriver wd;
 // private boolean acceptNextAlert = true;
//  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    String s = System.setProperty("webdriver.gecko.driver", "C:/DevelDlen/java_lesson_b7/geckodriver/geckodriver.exe");
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login();

  }

  private void login() {
    wd.get("http://localhost/addressbook/");
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testContactDelete() throws Exception {
    gotoHomePage();
    selectedContacts();
    deleteSelectedContacts();
    gotoHomePage();
  }

  private void selectedContacts() {
    wd.findElement(By.name("selected[]")).click();
  }

  private void deleteSelectedContacts() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
    wd.switchTo().alert().accept();
  }

  private void gotoHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();

  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
