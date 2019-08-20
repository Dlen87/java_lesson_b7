package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().creationGroup(new GroupData("test21", null, null));
        app.getGroupHelper().returnToGroupPage();
        app.getNavigationHelper().loginOut();
    }


}
