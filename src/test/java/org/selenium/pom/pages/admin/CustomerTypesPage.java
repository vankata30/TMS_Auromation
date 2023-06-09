package org.selenium.pom.pages.admin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.selenium.pom.base.BasePage;

import java.time.Duration;

public class CustomerTypesPage extends BasePage {
    JavascriptExecutor js;

    public CustomerTypesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }
    public String name;
    public String newName;
    public String checkboxStatus;
    @FindBy(xpath = "//a[@href='/admin']")
    public WebElement adminBtn;
    @FindBy(xpath = "//*[text()='Campaign Customer Types']")
    public WebElement campaignCustTypesBtn;
    @FindBy(xpath = "//*[text()=' Create New Campaign Customer Type ']")
    public WebElement createNewCampaignCutsBtn;
    @FindBy(xpath = "//input[@name='name']")
    public WebElement nameField;
    @FindBy(css = "span.mat-checkbox-inner-container")
    public WebElement activeCustTypeCheckbox;
    @FindBy(xpath = "//*[text()=\"Create\"]")
    public WebElement createBtn;
    @FindBy(xpath = "(//span[contains(text(),\"edit\")])[last()]")
    WebElement editIcon;
    @FindBy (xpath = "(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[last()]")
    public WebElement lastCustType;
    @FindBy(xpath = "//*[text()=\"Edit\"]")
    public WebElement editBtn;

    public CustomerTypesPage createNewCustomerType(){
        wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(campaignCustTypesBtn)).click();
        name = generateRandomName();
        wait.until(ExpectedConditions.elementToBeClickable(createNewCampaignCutsBtn)).click();
        nameField.click();
        nameField.sendKeys(name);
        activeCustTypeCheckbox.click();
        createBtn.click();
        wait.until(ExpectedConditions.textToBePresentInElement(lastCustType,name));
        return new CustomerTypesPage(driver);
    }
    public void editCustomerType(){
        js = (JavascriptExecutor) driver;
        newName = generateRandomName();
        js.executeScript("arguments[0].scrollIntoView(true);", editIcon);
        js.executeScript("arguments[0].click();", editIcon);
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).click();
        nameField.click();
        nameField.clear();
        nameField.sendKeys(newName);
        checkboxStatus = clickIsActiveCheckbox(activeCustTypeCheckbox);
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(lastCustType));
        wait.until(ExpectedConditions.textToBePresentInElement(lastCustType,newName));
    }
}
