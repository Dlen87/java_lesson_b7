package by.stqa.lesson2.addressbook.appmanager;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import by.stqa.lesson2.addressbook.modal.GroupData;
import by.stqa.lesson2.addressbook.modal.Groups;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHelper{

    private Contacts contactCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactCreation(ContactData contactData, boolean contactForm) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
     //   attach(By.name("photo"), contactData.getPhoto());
        wasBornUser(contactData.getBday(), contactData.getBmonth(), contactData.getByear());
        if (contactForm){
           if (contactData.getGroups().size() > 0){
                    Assert.assertTrue(contactData.getGroups().size() == 1);
                    try{  new Select(wd.findElement(By.name("new_group")))
                            .selectByVisibleText(contactData.getGroups().iterator().next().getName());
                } catch (NoSuchElementException ex){
                        return;
                    }
           }
        }
        else{
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    private void wasBornUser(String bday, String bmonth, String byear) {
        typeSelect(bday, By.name("bday"));
        typeSelect(bmonth, By.name("bmonth"));
        type(By.name("byear"), byear);
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void selectedContacts(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectedContactsById(int id) {
        wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        try{
            wd.findElement(By.cssSelector("div.msgbox"));
        }
        catch (NoSuchElementException e){
            return;
        }
    }

    public void delete(ContactData contact) {
        selectedContactsById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;
    }

    public void move(ContactData contact, String group) {
        selectedContactsById(contact.getId());
        int idSelectedGroup = selectGroupForContact(group);
        if (contact.getGroups().size() != 0) {
            int idGroupContact = contact.getGroups().iterator().next().getId();
            if (idGroupContact != idSelectedGroup) {
                addToGroup();
                returnPageElementsGroup(group);
            }
        }else {
            addToGroup();
            returnPageElementsGroup(group);
        }
    }

    public int selectGroupForContact(String group) {
        click(By.cssSelector("select[name=\"to_group\"]"));
        new Select(wd.findElement(By.cssSelector("select[name=\"to_group\"]"))).selectByVisibleText(group);
        int id = getIdSelectedGroup();
        click(By.cssSelector("select[name=\"to_group\"] > option[value=\"" + id + "\"]"));
        return id;
    }

    public int getIdSelectedGroup() {
        return Integer.parseInt(wd.findElement(By.cssSelector("select[name=\"to_group\"]")).getAttribute("value"));
    }


    public void deleteFromGroup(ContactData contact, String group) {
        int idGroup = 1;
        Groups groups = contact.getGroups();
        for (GroupData g : groups){
            if (group.equals(g.getName())){
                idGroup = g.getId();
            }
        }
        selectGroupForDeleteContact(group, idGroup);

        selectedContactsById(contact.getId());

        removeFromGroup();
        returnPageElementsGroup(group);
    }

    private void selectGroupForDeleteContact(String group, int idGroup) {
        click(By.xpath("//select[@name='group']"));
        new Select(wd.findElement(By.xpath("//select[@name='group']"))).selectByVisibleText(group);
        click(By.xpath("//option[@value='" + idGroup + "']"));
    }

    private void removeFromGroup() {
        click(By.xpath("//input[@name='remove']"));
    }

    private void addToGroup() {
        click(By.cssSelector("input[name=\"add\"]"));
    }

    private void returnPageElementsGroup(String group) {
               click(By.linkText("group page \"" + group + "\""));
    }

    public void cretion(ContactData contact) {
        addNewContact();
        fillContactCreation(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectedContactsById(contact.getId());
        modificationSelectedContact(contact.getId());
        fillContactCreation(contact, false);
        updateSelectedContact();
        contactCache = null;
        returnToHomePage();
    }

    public void modificationSelectedContact(int index) {
        click(By.xpath("//a[@href='edit.php?id="+ index +"']"));
    }

    public void updateSelectedContact() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> allRows = wd.findElements(By.name("entry"));
        for (WebElement row : allRows){
            try {
                List<WebElement> allCells = row.findElements(By.tagName("td"));
                String lastName = allCells.get(1).getText();
                String firstName = allCells.get(2).getText();
                String address = allCells.get(3).getText();
                String allEmails = allCells.get(4).getText();
                String allPhones = allCells.get(5).getText();
                int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
                contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                        .withAddress(address).withAllPhones(allPhones).withAllemails(allEmails));
            }
            catch (StaleElementReferenceException e){
                e.getMessage();
            }
        }
        return new Contacts(contactCache);
    }

    public void initContactModificationById(int id) {

        wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();

    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value").replaceAll(" ","");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname)
                .withLastname(lastname)
                .withHomephone(home).withMobile(mobile).withWorkphone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withAddress(address);
    }

    public void addFirstContatc(Set<GroupData> group) {
        cretion(new ContactData()
                .withFirstname("CAnna").withMiddlename("Ivanovna").withLastname("CDunian")
                .withNickname("CKat").withCompany("FIBA2").withAddress("Russia")
                .withHomephone("99-8-96").withMobile("375-29-3456789").withEmail("FDunian@mail.ru")
                .withBday("19").withBmonth("May").withByear("1987")
                .withGroups(group));
    }
}
