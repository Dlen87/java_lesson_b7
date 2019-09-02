package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Comparator;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"DEra", "Oko", "Dako", "EVova", "IBA2", "Russia", "99896", "1003456789", "putin@mail.ru", "Moscow", "123", "15", "March", "1986", "test3");
    app.getContactHelper().creationContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
   /* before.add(contact);
    Comparable<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after,before);*/
  }


}
