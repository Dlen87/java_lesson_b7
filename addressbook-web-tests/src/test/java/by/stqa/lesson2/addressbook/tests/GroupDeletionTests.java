package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().cretion(new GroupData().withName("test1"));
    }
  }

  @Test(enabled = false)
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group().list();
    int idSelect = before.size() - 2;
    app.group().delete(idSelect);
    List<GroupData> after = app.group().list();
   // Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(idSelect);
    Assert.assertEquals(after,before);
  }




}
