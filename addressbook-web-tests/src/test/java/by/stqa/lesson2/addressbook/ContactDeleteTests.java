package by.stqa.lesson2.addressbook;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase{

  @Test
  public void testContactDelete() throws Exception {
    gotoHomePage();
    selectedContacts();
    deleteSelectedContacts();
    gotoHomePage();
  }


}
