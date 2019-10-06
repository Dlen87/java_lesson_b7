package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.appmanager.ApplicationManager;
import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import by.stqa.lesson2.addressbook.modal.GroupData;
import by.stqa.lesson2.addressbook.modal.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")){
            Groups dbGroup = app.db().groups();
            Groups uiGroup = app.group().all();
            assertThat(uiGroup,equalTo(dbGroup.stream().map((g) -> new GroupData()
                    .withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
        }

    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")){
            Contacts dbContact = app.db().contacts();
            Contacts uiContact = app.contact().all();
            assertThat(uiContact,equalTo(dbContact.stream().map((g) -> new ContactData()
                    .withId(g.getId()).withLastname(g.getLastname())
                    .withFirstname(g.getFirstname()).withEmail(g.getEmail())
                    .withMobile(g.getMobile()).withAddress(g.getAddress())).collect(Collectors.toSet())));
        }

    }
}
