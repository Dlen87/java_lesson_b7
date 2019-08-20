package by.stqa.lesson2.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase{

  @Test
  public void testContactDelete() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectedContacts();
    app.getContactHelper().deleteSelectedContacts();
    //app.getNavigationHelper().gotoHomePage();
  }


}
