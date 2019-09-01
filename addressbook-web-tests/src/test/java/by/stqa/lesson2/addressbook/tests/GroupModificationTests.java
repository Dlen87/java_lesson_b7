package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().creationGroup(new GroupData("test1", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().modificationSelectGroup();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(),"test9", null, null);
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().updateSelectGroup();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData>  after = app.getGroupHelper().getGroupList();
      //  Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }
}
