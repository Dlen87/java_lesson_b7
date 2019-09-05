package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        if ( app.contact().list().size() == 0){
            app.contact().cretion(new ContactData(null, null, "Test1", null, null, null, null, null, null, null, null, "1", "January", "1900", "test0"));
        }
    }

    @Test (enabled = true)
    public void testGroupModification() throws InterruptedException {

        List<ContactData> before = app.contact().list();
        int contSelect = before.size()-1;
        int idEdit = before.get(contSelect).getId();
        ContactData contact = new ContactData(idEdit,"kABaSVNet", null, "lANaSVTetina", null, null, null, null, "1003456789", "putin@mail.ru", "Moscow", "123", "15", "March", "1986", null);
        app.contact().modify(contSelect, idEdit, contact);
        List<ContactData> after = app.contact().list();
      //  Assert.assertEquals(after.size(), before.size());
        before.remove(contSelect);
        before.add(contact);
        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after,before);
    }


}
