package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().creationContact(new ContactData("Anna", "Ivanovna", "Avomirnova", "EVova", "IBA2", "Russia", "99896", "1003456789", "putin@mail.ru", "Moscow", "123", "5", "March", "1986", "test3"));
    app.getNavigationHelper().loginOut();
  }


}
