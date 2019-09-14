package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts(){
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/foto.jpg");
    list.add(new Object[]{new ContactData() .withFirstname("TInna").withLastname("TCoco")
            .withHomephone("19-89-06").withMobile("+375(29)1456789").withEmail("ukan1@mail.ru")
            .withAddress("USA, Chicago, Vovetckaia,2-48").withPhoto(photo)
            .withBday("25").withBmonth("April").withByear("1981").withGroup("Group1")});
    list.add(new Object[]{new ContactData() .withFirstname("ATInna").withLastname("ATCoco")
            .withHomephone("29-89-06").withMobile("+375(29)2456789").withEmail("ukan2@mail.ru")
            .withAddress("USA, Chicago, Vovetckaia,3-48").withPhoto(photo)
            .withBday("22").withBmonth("April").withByear("1982").withGroup("Group1")});
    return list.iterator();
  }

  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().cretion(contact);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}
