package by.stqa.lesson2.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    addNewContact();
    fillContactCreation(new ContactData("MElena", "Ivanovna", "Ivanova", "EVova", "IBA2", "Russia", "99896", "1003456789", "putin@mail.ru", "Moscow", "123", "5", "March", "1986"));
    submitContactCreation();
    returnToHomePage();
    loginOut();
  }


}
