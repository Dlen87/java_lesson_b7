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
        Contacts contactsAfter  = new Contacts();
        Boolean delete = false;
        String group ="";
        app.goTo().homePage();
        Groups before = app.db().groups();
        for (GroupData g : before){
            group = g.getName();
            contactsBefore = g.getContacts();
            if (contactsBefore.size() != 0){
                deleteContact = contactsBefore.iterator().next();
                delete = true;
                break;
            }
        }
        if (delete && !group.equals("")){
            app.contact().deleteFromGroup(deleteContact,group);
        }else {
            ContactData someContact = app.db().contacts().iterator().next();
            if (!group.equals("")) {
                app.goTo().homePage();
                app.contact().move(someContact, group);
            }
            Contacts contacts = app.db().contacts();
            for (ContactData c : contacts){
                if (c.getGroups().size() != 0){
                    someContact = c;
                    break;
                }
            }
            app.contact().deleteFromGroup(someContact,group);
        }
        Groups after = app.db().groups();
        for (GroupData g : after){
            if (group.equals(g.getName())){
                contactsAfter = g.getContacts();
                break;
            }
        }
        assertThat(contactsAfter, equalTo(contactsBefore.withOut(deleteContact)));

    }


}

