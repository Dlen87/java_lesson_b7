package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().creationGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int idSelect = before.size() - 2;
    app.getGroupHelper().deleteGroup(idSelect);
    List<GroupData> after = app.getGroupHelper().getGroupList();
   // Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(idSelect);
    Assert.assertEquals(after,before);
  }




}
