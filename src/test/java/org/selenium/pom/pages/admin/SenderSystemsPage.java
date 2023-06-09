package org.selenium.pom.pages.admin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class SenderSystemsPage extends BasePage {
    JavascriptExecutor js;

    public String name;
    public String newName;
    public String checkboxStatus;

    public SenderSystemsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[@href='/admin']")
    public WebElement adminBtn;
    @FindBy(xpath = "//*[text()='Sender Systems']")
    public WebElement senderSystemsBtn;
    @FindBy(xpath = "//*[text()=' Create New Sender System ']")
    public WebElement createSenderSystemBtn;
    @FindBy(xpath = "//input[@name='name']")
    public WebElement nameField;
    @FindBy(css = "span.mat-checkbox-inner-container")
    public WebElement activeSenderSystemCheckbox;
    @FindBy(xpath = "//*[text()=\"Create\"]")
    public WebElement createBtn;
    @FindBy (xpath = "(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[last()]")
    public WebElement lastSenderSystem;
    @FindBy(xpath = "//*[text()=\"Edit\"]")
    public WebElement editBtn;
    @FindBy(xpath = "(//span[contains(text(),\"edit\")])[last()]")
    WebElement editIcon;

    public SenderSystemsPage createNewSenderSystem(){
        wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(senderSystemsBtn)).click();
        name = generateRandomName();
        wait.until(ExpectedConditions.elementToBeClickable(createSenderSystemBtn)).click();
        nameField.click();
        nameField.sendKeys(name);
        activeSenderSystemCheckbox.click();
        createBtn.click();
        wait.until(ExpectedConditions.textToBePresentInElement(lastSenderSystem,name));
        return new SenderSystemsPage(driver);
    }

    public void editSenderSystem(){
        js = (JavascriptExecutor) driver;
        newName = generateRandomName();
        js.executeScript("arguments[0].scrollIntoView(true);", editIcon);
        js.executeScript("arguments[0].click();", editIcon);
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).click();
        nameField.click();
        nameField.clear();
        nameField.sendKeys(newName);
        checkboxStatus = clickIsActiveCheckbox(activeSenderSystemCheckbox);
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(lastSenderSystem));
        wait.until(ExpectedConditions.textToBePresentInElement(lastSenderSystem,newName));
    }
}
