package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import by.stqa.lesson2.addressbook.modal.GroupData;
import by.stqa.lesson2.addressbook.modal.Groups;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeleteFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        if (app.db().contacts().size() == 0){
            app.contact().cretion(new ContactData()
                    .withFirstname("Anna").withMiddlename("Ivanovna").withLastname("Dunian")
                    .withNickname("Kat").withCompany("IBA2").withAddress("Russia")
                    .withHomephone("99-8-96").withMobile("375-29-3456789").withEmail("Dunian@mail.ru")
                    .withBday("19").withBmonth("May").withByear("1987"));
        }
    }


    @Test
    public void testContactDeleteFromGroup() throws Exception {
        app.goTo().homePage();
        String groupSelected = "test1";
        Groups before = app.db().groups();
        GroupData selectedGroup = before.iterator().next();
        Contacts  contactsBefore = selectedGroup.getContacts();

        ContactData deleteContact = contactsBefore.iterator().next();
        //Contacts before = app.db().contacts();
        //ContactData deleteContact = before.iterator().next();
        app.contact().deleteFromGroup(deleteContact, groupSelected);



    }


}

