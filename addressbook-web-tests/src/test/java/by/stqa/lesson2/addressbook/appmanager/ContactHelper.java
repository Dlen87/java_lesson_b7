package by.stqa.lesson2.addressbook.appmanager;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.Contacts;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
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
        wasBornUser(contactData.getBday(), contactData.getBmonth(), contactData.getByear());
        if (contactForm){
            try{
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
            catch (NoSuchElementException ex){
                return;
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

    public void delete(int idSelect) {
        selectedContacts(idSelect);
        deleteSelectedContacts();
    }

    public void delete(ContactData contact) {
        selectedContactsById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;
    }

    public void cretion(ContactData contact) {
        addNewContact();
        fillContactCreation(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(int contSelect, int idEdit, ContactData contact) {
        selectedContacts(contSelect);
        modificationSelectedContact(idEdit);
        fillContactCreation(contact, false);
        updateSelectedContact();
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

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        String lastName = "";
        String firstName = "";
        int n = 0;
        List<WebElement> allRows = wd.findElements(By.name("entry"));
        for (WebElement row : allRows){
           try {
               List<WebElement> allCells = row.findElements(By.tagName("td"));
               for (WebElement cell : allCells){
                   if (n == 1){
                       lastName = cell.getText();
                   }
                   else if (n == 2){
                       firstName = cell.getText();
                   }
                   else if (n > 2){
                       n = 0;
                       break;
                   }
                   n++;

               }
               int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
               contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
           }
           catch (StaleElementReferenceException e){
               e.getMessage();
           }
        }
        return contacts;
    }

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        String lastName = "";
        String firstName = "";
        int n = 0;
        List<WebElement> allRows = wd.findElements(By.name("entry"));
        for (WebElement row : allRows){
            try {
                List<WebElement> allCells = row.findElements(By.tagName("td"));
                for (WebElement cell : allCells){
                    if (n == 1){
                        lastName = cell.getText();
                    }
                    else if (n == 2){
                        firstName = cell.getText();
                    }
                    else if (n > 2){
                        n = 0;
                        break;
                    }
                    n++;

                }
                int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
                contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
            }
            catch (StaleElementReferenceException e){
                e.getMessage();
            }
        }
        return new Contacts(contactCache);
    }


}
