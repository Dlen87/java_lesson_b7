package by.stqa.lesson2.addressbook.tests;

 import by.stqa.lesson2.addressbook.modal.ContactData;
 import by.stqa.lesson2.addressbook.modal.Contacts;
 import by.stqa.lesson2.addressbook.modal.GroupData;
 import by.stqa.lesson2.addressbook.modal.Groups;
 import org.testng.annotations.*;

 import static org.hamcrest.CoreMatchers.equalTo;
 import static org.hamcrest.MatcherAssert.assertThat;

 import java.util.HashSet;
 import java.util.Set;

public class ContactAddToGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition(){
        GroupData newGroup = new GroupData().withName("test1").withHeader("header1").withFooter("footer1");
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().cretion(newGroup);
        }
        if (app.db().contacts().size() == 0){
            app.contact().cretion(new ContactData()
                    .withFirstname("CAnna").withMiddlename("Ivanovna").withLastname("CDunian")
                    .withNickname("CKat").withCompany("FIBA2").withAddress("Russia")
                    .withHomephone("99-8-96").withMobile("375-29-3456789").withEmail("FDunian@mail.ru")
                    .withBday("19").withBmonth("May").withByear("1987"));
        }
    }

    @Test
    public void testContactAddToGroup() throws Exception {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        int findGroup = 0;

        String groupSelected = groups.iterator().next().getName();
        ContactData moveContact = before.iterator().next();
        Groups  groupsBefore = moveContact.getGroups();

        findGroup = getFindGroup(findGroup, groupSelected, groupsBefore);

        if (findGroup == 0){
            app.contact().move(moveContact, groupSelected);
        }else {
            for (ContactData c : before){
                if (c.getGroups().size() == 0){
                    moveContact = c;
                    break;
                }
            }
            app.contact().move(moveContact, groupSelected);
        }

        Contacts after = app.db().contacts();
        Groups groupsAfter = after.iterator().next().getGroups();

        Groups groupsAdd = groupsBefore.withAdded(getDataSeletedGroup(groups, groupSelected));
        assertThat(groupsAfter, equalTo(groupsAdd));
    }

    private int getFindGroup(int findGroup, String groupSelected, Groups groupsBefore) {
        for (GroupData g : groupsBefore){
            if (groupSelected.equals(g.getName())){
                findGroup = 1;
            }
        }
        return findGroup;
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

