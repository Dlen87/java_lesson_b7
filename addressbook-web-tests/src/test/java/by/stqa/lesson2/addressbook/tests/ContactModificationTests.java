package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.contact().cretion(new ContactData()
                    .withFirstname("Anna").withMiddlename("Ivanovna").withLastname("Dunian")
                    .withNickname("Kat").withCompany("IBA2").withAddress("Russia")
                    .withHomephone("99-8-96").withMobile("375-29-3456789").withEmail("Dunian@mail.ru")
                    .withBday("19").withBmonth("May").withByear("1987")
                    .withGroup("test1"));
        }
    }

    @Test (enabled = true)
    public void testGroupModification() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("ALola").withLastname("ALeonova")
                .withBday("9").withBmonth("May").withByear("1989");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }


}
