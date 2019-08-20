package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().creationGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().modificationSelectGroup();
        app.getGroupHelper().fillGroupForm((new GroupData("testFFFF", "test111", "testAAA")));
        app.getGroupHelper().updateSelectGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
