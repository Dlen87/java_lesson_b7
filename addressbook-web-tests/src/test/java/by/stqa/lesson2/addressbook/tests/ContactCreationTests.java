package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Comparator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("UMAna").withMiddlename("Oko").withLastname("VAsssa")
            .withNickname("EVova").withCompany("IBA2").withAddress("Italy")
            .withHomephone("99896").withMobile("1003456789").withEmail("ukan@mail.ru")
            .withBday("12").withBmonth("March").withByear("1986")
            .withGroup("test3");
    app.contact().cretion(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}
