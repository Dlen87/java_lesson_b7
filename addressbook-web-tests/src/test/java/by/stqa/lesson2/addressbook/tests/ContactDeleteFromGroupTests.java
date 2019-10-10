package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import by.stqa.lesson2.addressbook.modal.GroupData;
import by.stqa.lesson2.addressbook.modal.Groups;
import org.testng.annotations.*;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeleteFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        GroupData newGroup = new GroupData().withName("test1").withHeader("header1").withFooter("footer1");
        Set<GroupData> group = new HashSet<>();
        group.add(newGroup);

        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().cretion(newGroup);
            app.contact().addFirstContatc(group);
        }
        if (app.db().contacts().size() == 0){
            app.contact().addFirstContatc(group);
        }
    }

    @Test
    public void testContactDeleteFromGroup() throws Exception {
        ContactData deleteContact = new ContactData();
        Contacts contactsBefore = new Contacts();
        Contacts contactsAfter = new Contacts();
    //    int count = app.db().contacts().size();
        Boolean delete = false;
        GroupData group = new GroupData();
        app.goTo().homePage();
        Groups before = app.db().groups();
        for (GroupData g : before){
            //group = g.getName();
            group = g;
            contactsBefore = g.getContacts();
            if (contactsBefore.size() != 0){
                deleteContact = contactsBefore.iterator().next();
                delete = true;
                break;
            }
        }
        if (!delete){
            deleteContact = app.db().contacts().iterator().next();
            app.goTo().homePage();
            app.contact().move(deleteContact, group);
            contactsBefore = getListContacts(contactsBefore, group.getName());
            Contacts contacts = app.db().contacts();
            for (ContactData c : contacts){
                if (c.getId() == deleteContact.getId()){
                    deleteContact = c;
                    break;
                }
            }
        }
        app.contact().deleteFromGroup(deleteContact, group);

        contactsAfter = getListContacts(contactsAfter, group.getName());

        assertThat(contactsAfter, equalTo(contactsBefore.withOut(deleteContact)));

    }

    private Contacts getListContacts(Contacts contacts, String group) {
        Groups before = app.db().groups();
        for (GroupData g : before){
            if (group.equals(g.getName())){
                contacts = g.getContacts();
            }
        }
        return contacts;
    }


}

