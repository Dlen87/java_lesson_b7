package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectedContacts();
        app.getContactHelper().modificationSelectedContact();
        app.getContactHelper().fillContactCreation(new ContactData("A2", "PIvanovna", "A1", "EVova", "IBA2", "Russia", "99896", "1003456789", "putin@mail.ru", "Moscow", "123", "15", "March", "1986"));
        app.getContactHelper().updateSelectedContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
