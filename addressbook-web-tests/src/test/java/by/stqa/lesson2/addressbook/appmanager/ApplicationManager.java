package by.stqa.lesson2.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    public WebDriver wd;
    private String browser;
    private String target;//
    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private DbHelper dbHelper;

    public ApplicationManager(String browser, String  target) {
        this.browser = browser;
        this.target = target; //
        properties = new Properties();
    }

    public void init() throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));//
        dbHelper = new DbHelper();

        if ("".equals(properties.getProperty("selenium.server"))){
            if (browser.equals(BrowserType.FIREFOX)){
                wd = new FirefoxDriver();
            }
            else if (browser.equals(BrowserType.CHROME)){
                wd = new ChromeDriver();
            }
            else if (browser.equals(BrowserType.IE)){
                wd = new InternetExplorerDriver();
            }
        }else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }

        wd.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"),
                            properties.getProperty("web.adminPassword"));
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);

    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public DbHelper db(){return dbHelper;}
}
