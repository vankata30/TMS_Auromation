package org.selenium.pom.pages.admin;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.util.List;

public class MessageTriggersPage extends BasePage {
    JavascriptExecutor js;

    public String name;
    public String newName;

    public String checkboxStatus;
    public MessageTriggersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[@href='/admin']")
    public WebElement adminBtn;
    @FindBy (className = "overlay")
    public WebElement overlay;
    @FindBy(xpath = "//*[text()='Message Triggers']")
    public WebElement triggersBtn;
    @FindBy(xpath = "//*[text()=' Create New Message Trigger ']")
    public WebElement createNewTriggerBtn;
    @FindBy(xpath = "//input[@name='name']")
    public WebElement nameField;
    @FindBy(css = "span.mat-checkbox-inner-container")
    public WebElement activeTriggerCheckbox;
    @FindBy(xpath = "(//span[contains(text(),\"edit\")])[last()]")
    WebElement editIcon;
    @FindBy(xpath = "//*[text()=\"Create\"]")
    public WebElement createBtn;
    @FindBy(xpath = "//button[@aria-label=\"Next page\"]")
    public WebElement nextPageArrow;
    @FindBy (xpath = "(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[last()]")
    public WebElement lastTrigger;
    @FindBy(xpath = "//*[text()=\"Edit\"]")
    public WebElement editBtn;

    public MessageTriggersPage createNewMessageTrigger(){
        wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(triggersBtn)).click();
        name = generateRandomName();
        wait.until(ExpectedConditions.elementToBeClickable(createNewTriggerBtn)).click();
        nameField.click();
        nameField.sendKeys(name);
        activeTriggerCheckbox.click();
        createBtn.click();
        wait.until(ExpectedConditions.textToBePresentInElement(lastTrigger,name));
//        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(entriesLocator,entries.size()));
        return new MessageTriggersPage(driver);
    }

    public void editTriggerMessage(){
        js = (JavascriptExecutor) driver;
        newName = generateRandomName();
        js.executeScript("arguments[0].scrollIntoView(true);", editIcon);
        js.executeScript("arguments[0].click();", editIcon);
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).click();
        nameField.click();
        nameField.clear();
        nameField.sendKeys(newName);
        checkboxStatus = clickIsActiveCheckbox(activeTriggerCheckbox);
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(lastTrigger));
    }
}
