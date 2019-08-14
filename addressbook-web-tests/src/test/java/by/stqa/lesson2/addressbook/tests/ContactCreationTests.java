package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().addNewContact();
    app.getContactHelper().fillContactCreation(new ContactData("MElena", "Ivanovna", "Ivanova", "EVova", "IBA2", "Russia", "99896", "1003456789", "putin@mail.ru", "Moscow", "123", "5", "March", "1986"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
    app.getNavigationHelper().loginOut();
  }


}
