package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ContactDeleteTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if ( app.contact().list().size() == 0){
      app.contact().cretion(new ContactData()
              .withFirstname("Anna").withMiddlename("Ivanovna").withLastname("Dunian")
              .withNickname("Kat").withCompany("IBA2").withAddress("Russia")
              .withHomephone("99-8-96").withMobile("375-29-3456789").withEmail("Dunian@mail.ru")
              .withBday("19").withBmonth("May").withByear("1987")
              .withGroup("test1"));
    }
  }

  @Test (enabled = true)
  public void testContactDelete() throws Exception {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(deletedContact)));
  }




}
