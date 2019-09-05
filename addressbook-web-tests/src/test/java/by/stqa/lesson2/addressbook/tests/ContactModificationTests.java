package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testGroupModification() throws InterruptedException {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().creationContact(new ContactData(null, null, "Test1", null, null, null, null, null, null, null, null, "1", "January", "1900", "test0"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectedContacts(before.size()-1);
        app.getContactHelper().modificationSelectedContact();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"aSVNet", null, "aSVTetina", null, null, null, null, "1003456789", "putin@mail.ru", "Moscow", "123", "15", "March", "1986", null);
        app.getContactHelper().fillContactCreation(contact, false);
        app.getContactHelper().updateSelectedContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
      //  Assert.assertEquals(after.size(), before.size());
        before.remove(before.size()-1);
      //  Thread.sleep(250);
        before.add(contact);
        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after,before);
    }
}
