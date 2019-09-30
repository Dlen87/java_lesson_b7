package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (app.db().contacts().size() == 0){
      app.contact().cretion(new ContactData()
              .withFirstname("Anna").withMiddlename("Ivanovna").withLastname("Dunian")
              .withNickname("Kat").withCompany("IBA2").withAddress("Russia")
              .withHomephone("99-8-96").withMobile("375-29-3456789").withEmail("Dunian@mail.ru")
              .withBday("19").withBmonth("May").withByear("1987")
             // .withGroup("test1")
      );
    }
  }

  @Test (enabled = true)
  public void testContactDelete() throws Exception {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.withOut(deletedContact)));
    verifyContactListInUI();
  }




}
