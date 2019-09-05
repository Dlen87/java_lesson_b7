package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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

    List<ContactData> before = app.contact().list();
    int idSelect = before.size() - 1;
    app.contact().delete(idSelect);
    Thread.sleep(200); // время для удаления контакта
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
   // Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(idSelect);
    Assert.assertEquals(after, before);
  }




}
