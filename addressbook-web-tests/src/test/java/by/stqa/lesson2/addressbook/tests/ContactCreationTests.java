package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
  //  Contacts before = app.contact().all();
    File photo = new File("src/test/resources/foto.jpg");
    ContactData contact = new ContactData()
            .withFirstname("Inna").withMiddlename("Asa").withLastname("Coco")
            .withNickname("EVova").withCompany("IBA3")
            .withHomephone("99-89-06").withMobile("+375(29)3456789").withEmail("ukan@mail.ru")
            .withAddress("USA, Chicago, Street Sovetckaia, home 1-48")
            .withBday("5").withBmonth("April").withByear("1991")
            .withGroup("Group1").withPhoto(photo);
    app.contact().cretion(contact);
  /*  Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));*/
  }


}
