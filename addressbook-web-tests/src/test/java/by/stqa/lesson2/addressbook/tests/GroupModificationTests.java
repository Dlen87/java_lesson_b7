package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().modificationSelectGroup();
        app.getGroupHelper().fillGroupForm((new GroupData("test111", "test111", "testAAA")));
        app.getGroupHelper().updateSelectGroup();
        app.getNavigationHelper().returnToGroupPage();
    }
}
