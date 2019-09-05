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
      app.contact().cretion(new ContactData("Anna", "Ivanovna", "Avomirnova", "EVova", "IBA2", "Russia", "99896", "1003456789", "putin@mail.ru", "Moscow", "123", "5", "March", "1986", "test4"));
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
