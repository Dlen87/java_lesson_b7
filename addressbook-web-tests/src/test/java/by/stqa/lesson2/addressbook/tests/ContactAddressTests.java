package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        if ( app.contact().all().size() == 0){
            app.contact().cretion(new ContactData()
                    .withFirstname("Ivan").withMiddlename("Ivanovitch").withLastname("Ivanov")
                    .withNickname("Ivanko").withCompany("IBA1")
                    .withAddress("Russia, Moscow, Street Sovetckaia, home 98-78")
                    .withHomephone("+375(29)99-8-96").withMobile("8-29-3456789").withWorkphone("33 33 33")
                    .withEmail("Ivanov@mail.ru").withEmail2("I_vanov@yandex.ru").withEmail3("I.vanov@gmail.com")
                    .withBday("19").withBmonth("May").withByear("1987")
                    .withGroup("test1"));
        }
    }

    @Test
    public void testContactAddress(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
    }
}
