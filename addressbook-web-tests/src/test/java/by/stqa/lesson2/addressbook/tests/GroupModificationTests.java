package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().cretion(new GroupData().withName("test1"));
        }
    }

    @Test(enabled = true)
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        int idSelect = before.size() - 2;
        GroupData group = new GroupData().withId(before.get(idSelect).getId()).withName("test132");
        app.group().modify(idSelect, group);
        List<GroupData> after = app.group().list();
        //  Assert.assertEquals(after.size(), before.size());
        before.remove(idSelect);
        before.add(group);
        Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}

