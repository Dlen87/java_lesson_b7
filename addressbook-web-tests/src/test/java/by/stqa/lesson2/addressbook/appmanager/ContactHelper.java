package by.stqa.lesson2.addressbook.appmanager;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper{

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
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
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

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void modificationSelectedContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void updateSelectedContact() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void creationContact(ContactData contact) {
        addNewContact();
        fillContactCreation(contact, true);
        submitContactCreation();
        returnToHomePage();
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        String lastName = "";
        String firstName = "";
        int n = 0;
       // List<WebElement> allRows = wd.findElements(By.xpath("//*[@id='maintable']//tr[@name='entry']"));
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
               ContactData contact = new ContactData( id,firstName, null, lastName, null, null, null, null, null, null, null, null, null, null, null, null);
               contacts.add(contact);
           }
           catch (StaleElementReferenceException e){
               e.getMessage();
           }
        }
        return contacts;
    }
}
