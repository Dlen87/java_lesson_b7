package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().creationContact(new ContactData(null, null, "Test1", null, null, null, null, null, null, null, null, "1", "January", "1900", "test0"));
        }
        app.getContactHelper().selectedContacts();
        app.getContactHelper().modificationSelectedContact();
        app.getContactHelper().fillContactCreation(new ContactData("Era", "PIvanovna", "CSAADAsh", "EVova", "IBA2", "Russia", "99896", "1003456789", "putin@mail.ru", "Moscow", "123", "15", "March", "1986", null), false);
        app.getContactHelper().updateSelectedContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
