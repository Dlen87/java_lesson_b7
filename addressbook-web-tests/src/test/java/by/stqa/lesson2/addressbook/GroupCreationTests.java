package by.stqa.lesson2.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test10", "test122", "test1222"));
        app.submitGroupCreation();
        app.returnToGroupPage();
        app.loginOut();
    }


}
