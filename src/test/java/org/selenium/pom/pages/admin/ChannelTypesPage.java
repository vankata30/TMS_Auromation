package org.selenium.pom.pages.admin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.awt.*;

public class ChannelTypesPage extends BasePage {

    JavascriptExecutor js;
    public String expectedStatus;

    public ChannelTypesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[@href='/admin']")
    public WebElement adminBtn;
    @FindBy(xpath = "//*[text()='Channel Types']")
    public WebElement channelTypesBtn;
    @FindBy(xpath = "//*[text()=' Create New Channel ']")
    public WebElement createNewChannelBtn;
    @FindBy(xpath = "//input[@name='name']")
    public WebElement nameField;
    @FindBy(id = "requestTypeEmails")
    public WebElement requestTypesDropdown;
    @FindBy(xpath = "//span[text()=' Create ']")
    public WebElement createBtn;
    @FindBy(xpath = "(//span[contains(text(),\"edit\")])[last()]")
    public WebElement editIcon;
    @FindBy(xpath = "//*[text()=\" Edit \"]")
    public WebElement editBtn;
    @FindBy(xpath ="//mat-option[@role='option'][1]")
    public WebElement standardRequestOption;

    @FindBy(xpath ="//mat-option[@role='option'][2]")
    public WebElement bulkMessagesOption;
    @FindBy(xpath ="//mat-option[@role='option'][3]")
    public WebElement campaignRequestOption;
    @FindBy(xpath = "//mat-checkbox[@name='isActive']")
    public WebElement activeChannelCheckbox;
    public String expectedRequestTypes;
    public String lastItemActiveStatus;
    @FindBy(xpath = "(//*[@class='mat-cell cdk-cell cdk-column-requestTypes mat-column-requestTypes ng-star-inserted'])[last()]")
    public WebElement actualRequestTypes;
    @FindBy(xpath = "(//*[@class='mat-cell cdk-cell cdk-column-isActive mat-column-isActive ng-star-inserted'])[last()]")
    public WebElement actualActiveStatus;
    @FindBy(xpath = "(//*[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[last()]")
    public WebElement actualLastItemName;

    public void editChannelType() throws AWTException {
        js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(channelTypesBtn)).click();
        js.executeScript("arguments[0].scrollIntoView(true);", editIcon);
        js.executeScript("arguments[0].click();", editIcon);
//        String isActive = lastItemActiveStatus;
        wait.until(ExpectedConditions.elementToBeClickable(requestTypesDropdown)).click();
        checkUncheckedBoxes();
        pressEscape();
        expectedStatus = clickIsActiveCheckbox();
//        activeChannelCheckbox.click();
        editBtn.click();
        wait.until(ExpectedConditions.textToBePresentInElement(actualRequestTypes, expectedRequestTypes));

    }

    public String clickIsActiveCheckbox(){
        activeChannelCheckbox.click();
        if (activeChannelCheckbox.getAttribute("class").toString().contains("checked")){
            return "true";
        }else {
            return "false";
        }
    }
    void checkUncheckedBoxes(){
        if (standardRequestOption.getAttribute("aria-selected").toString().equals("true")
                && campaignRequestOption.getAttribute("aria-selected").toString().equals("true")
                && bulkMessagesOption.getAttribute("aria-selected").toString().equals("true")){
            campaignRequestOption.click();
            expectedRequestTypes = "Standard Request, Bulk Messages";
        } else {
            if (standardRequestOption.getAttribute("aria-selected").toString().equals("false")) {
                standardRequestOption.click();

            }
            if (bulkMessagesOption.getAttribute("aria-selected").toString().equals("false")) {
                bulkMessagesOption.click();
            }
            if (campaignRequestOption.getAttribute("aria-selected").toString().equals("false")) {
                campaignRequestOption.click();
            }
            expectedRequestTypes = "Standard Request, Bulk Messages, Campaign Request";
        }
    }
}
