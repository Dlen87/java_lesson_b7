package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import by.stqa.lesson2.addressbook.modal.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[]{new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().cretion(group);
        Groups after = app.group().all();
        assertThat(after, equalTo(
               before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test(enabled = false)
    public void testBadGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'");
        app.group().cretion(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
