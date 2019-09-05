package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

//import static jdk.vm.ci.sparc.SPARC.o1;

public class GroupCreationTests extends TestBase {

    @Test(enabled = false)
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("test879");
        app.group().cretion(group);
        List<GroupData> after = app.group().list();
        //Assert.assertEquals(after.size(), before.size() + 1);
        before.add(group);
        Comparator<? super GroupData> byId =  (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after,before);

    }


}
