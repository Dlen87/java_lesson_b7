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

        ContactData moveContact = before.iterator().next();
        Groups  groupsBefore = moveContact.getGroups();
        GroupData groupSelected = getGroupSelected(groups, before);

        findGroup = getFindGroup(findGroup, groupSelected.getName(), groupsBefore);

        if (findGroup != 0){
            for (ContactData c : before){
                findGroup = 0;
                if (c.getId() != moveContact.getId() && c.getGroups().size() < groups.size()){
                    if ( getFindGroup(findGroup, groupSelected.getName(), c.getGroups()) == 0){
                        moveContact = c;
                        groupsBefore = moveContact.getGroups();
                        break;
                    }
                }
            }
        }
        app.contact().move(moveContact, groupSelected);

        Contacts after = app.db().contacts();
        groups = app.db().groups();
        Groups groupsAfter = getGroupsContactAfter(after, moveContact.getId());

        Groups groupsAdd = groupsBefore.withAdded(getDataSeletedGroup(groups, groupSelected.getName()));
        assertThat(groupsAfter, equalTo(groupsAdd));
    }

    private GroupData getGroupSelected(Groups groups, Contacts before) {
        GroupData groupSelected = new GroupData();
        int allGroupFull = 1;
        for (GroupData g : groups){
            if (g.getContacts().size() < before.size()){
                allGroupFull = 0;
                groupSelected = g;
                break;
            }
        }
        if (allGroupFull == 1) {
            app.group().createNewGroup();
            groups = app.db().groups();
            groupSelected = getEmptyGroup(groups);
        }
        return groupSelected;
    }

    private GroupData getEmptyGroup(Groups groups) {
        GroupData emptyGroup = new GroupData();
        for (GroupData g : groups){
            if (g.getContacts().size() == 0){
                emptyGroup = g;
            }
        }
        return emptyGroup;
    }


    private Groups getGroupsContactAfter(Contacts after, int idContact) {
        Groups groups = new Groups();
        for (ContactData c : after){
            if (c.getId() == idContact){
                groups = c.getGroups();
            }
        }
        return groups;
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

