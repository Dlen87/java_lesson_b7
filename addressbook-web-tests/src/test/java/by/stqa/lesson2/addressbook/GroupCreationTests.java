package by.stqa.lesson2.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test10", "test122", "test1222"));
        submitGroupCreation();
        returnToGroupPage();
        loginOut();
    }


}
