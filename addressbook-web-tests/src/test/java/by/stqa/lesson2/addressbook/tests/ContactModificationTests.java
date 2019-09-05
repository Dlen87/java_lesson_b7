package by.stqa.lesson2.addressbook.tests;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
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

        List<ContactData> before = app.contact().list();
        int contSelect = before.size()-1;
        int idEdit = before.get(contSelect).getId();
        ContactData contact = new ContactData()
                .withId(idEdit).withFirstname("kABaSVNet").withLastname("lANaSVTetina");
        app.contact().modify(contSelect, idEdit, contact);
        List<ContactData> after = app.contact().list();
      //  Assert.assertEquals(after.size(), before.size());
        before.remove(contSelect);
        before.add(contact);
        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after,before);
    }


}
