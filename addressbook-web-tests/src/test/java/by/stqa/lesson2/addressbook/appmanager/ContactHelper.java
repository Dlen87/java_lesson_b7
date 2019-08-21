package by.stqa.lesson2.addressbook.appmanager;

import by.stqa.lesson2.addressbook.modal.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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

    public void selectedContacts() {
        click(By.name("selected[]"));
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
}
