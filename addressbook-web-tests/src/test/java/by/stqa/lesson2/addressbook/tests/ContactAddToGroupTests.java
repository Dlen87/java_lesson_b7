package by.stqa.lesson2.addressbook.tests;

 import by.stqa.lesson2.addressbook.modal.ContactData;
 import by.stqa.lesson2.addressbook.modal.Contacts;
 import by.stqa.lesson2.addressbook.modal.GroupData;
 import by.stqa.lesson2.addressbook.modal.Groups;
 import org.testng.annotations.*;

 import static org.hamcrest.CoreMatchers.equalTo;
 import static org.hamcrest.MatcherAssert.assertThat;

 import org.openqa.selenium.*;

public class ContactAddToGroupTests extends TestBase{

    private Boolean cretionGroup = false;

    @BeforeMethod
    public void ensurePrecondition(){
        if (app.db().contacts().size() == 0){
            app.contact().cretion(new ContactData()
                    .withFirstname("Anna").withMiddlename("Ivanovna").withLastname("Dunian")
                    .withNickname("Kat").withCompany("IBA2").withAddress("Russia")
                    .withHomephone("99-8-96").withMobile("375-29-3456789").withEmail("Dunian@mail.ru")
                    .withBday("19").withBmonth("May").withByear("1987"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().cretion(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
            cretionGroup = true;
        }
    }


    @Test
    public void testContactAddToGroup() throws Exception {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        String groupSelected = "";
        if (cretionGroup){
            groupSelected = groups.iterator().next().getName();
        }else {
            groupSelected = "test2";
        }
        ContactData moveContact = before.iterator().next();
        Groups  groupsBefore = moveContact.getGroups();

        app.contact().move(moveContact, groupSelected);

        Contacts after = app.db().contacts();
        Groups groupsAfter = after.iterator().next().getGroups();

        if (groupsBefore.size() != groupsAfter.size()){
            Groups groupsAdd = groupsBefore.withAdded(getDataSeletedGroup(groups, groupSelected));
            assertThat(groupsAfter, equalTo(groupsAdd));
        }else{
            assertThat(groupsAfter, equalTo(groupsBefore));
        }
    }

    private GroupData getDataSeletedGroup(Groups groups, String groupSelected) {
        GroupData groupData = new GroupData();
        for (GroupData g : groups){
            if (g.getName().equals(groupSelected)){
                 groupData.withId(g.getId()).withName(g.getName()).withFooter(g.getFooter()).withHeader(g.getHeader());
            }
        }
        return groupData;
    }


}

