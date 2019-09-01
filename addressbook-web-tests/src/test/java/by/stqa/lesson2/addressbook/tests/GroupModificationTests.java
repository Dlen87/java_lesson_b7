package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
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
        app.getGroupHelper().selectGroup(before.size() - 2);
        app.getGroupHelper().modificationSelectGroup();
        GroupData group = new GroupData(before.get(before.size() - 2).getId(),"test12", null, null);
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().updateSelectGroup();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData>  after = app.getGroupHelper().getGroupList();
      //  Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 2);
        before.add(group);
        Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
