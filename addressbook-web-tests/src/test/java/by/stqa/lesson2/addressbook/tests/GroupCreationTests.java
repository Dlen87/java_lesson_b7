package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test999", null, null);
        app.getGroupHelper().creationGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        //Assert.assertEquals(after.size(), before.size() + 1);

        int max = 0;
        for (GroupData element : after){
            if (element.getId() > max){
                max = element.getId();
            }
        }
        group.setId(max);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

    }


}
