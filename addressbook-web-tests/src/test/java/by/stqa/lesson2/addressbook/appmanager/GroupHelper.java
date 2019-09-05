package by.stqa.lesson2.addressbook.appmanager;

import by.stqa.lesson2.addressbook.modal.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends BaseHelper{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void modificationSelectGroup() {
        click(By.name("edit"));
    }

    public void updateSelectGroup() {
        click(By.name("update"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void cretion(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void delete(int idSelect) {
        selectGroup(idSelect);
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public void modify(int idSelect, GroupData group) {
        selectGroup(idSelect);
        modificationSelectGroup();
        fillGroupForm(group);
        updateSelectGroup();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return  isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String nameGr = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id, nameGr, null, null);
            groups.add(group);
        }
        return groups;
    }
}
