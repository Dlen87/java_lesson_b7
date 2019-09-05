package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Comparator;

public class ContactCreationTests extends TestBase {

  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withFirstname("Ana").withMiddlename("Oko").withLastname("Asssa")
            .withNickname("EVova").withCompany("IBA2").withAddress("Russia")
            .withHomephone("99896").withMobile("1003456789").withEmail("putin@mail.ru")
            .withBday("15").withBmonth("March").withByear("1986")
            .withGroup("test3");
    app.contact().cretion(contact);
    List<ContactData> after = app.contact().list();
   // Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (d1, d2) -> Integer.compare(d1.getId(),d2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after,before);
  }


}
