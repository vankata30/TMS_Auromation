package org.selenium.pom.pages.admin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.awt.*;

public class ApproverGroupsPage extends BasePage {

    JavascriptExecutor js;

    public String name;
    public String newName;

    public String checkboxStatus;

    public ApproverGroupsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/admin']")
    WebElement adminBtn;
    @FindBy(xpath = "//*[text()='Request Approver Groups']")
    WebElement approverGroupsBtn;
    @FindBy(xpath = "//*[text()=' Create New Approver Group ']")
    WebElement createNewApproverGroupBtn;
    @FindBy(xpath = "//input[@name='name']")
    WebElement nameField;
    @FindBy(xpath = "//*[@role='combobox']")
    WebElement approversDropdwn;
    @FindBy(css = "span.mat-checkbox-inner-container")
    WebElement activeApproverGroupCheckbox;
    @FindBy(xpath = "//*[text()=\" Create \"]")
    WebElement createBtn;
    @FindBy(xpath = "//*[text()=\" Edit \"]")
    WebElement editBtn;
    @FindBy(xpath = "(//*[@class='mat-option-text'])[1]")
    WebElement firstApprover;
    @FindBy(xpath = "(//*[@class='mat-option-text'])[2]")
    WebElement secondApprover;
    @FindBy(xpath = "(//span[contains(text(),\"edit\")])[last()]")
    WebElement editIcon;

    @FindBy(xpath = "(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[last()]")
    WebElement lastApproverGroup;
    public String approver1;
    public String approver2;

    public ApproverGroupsPage createNewApproverGroup() throws AWTException {
        wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
        clickTabArrow();
        wait.until(ExpectedConditions.elementToBeClickable(approverGroupsBtn)).click();
        name = generateRandomName();
        wait.until(ExpectedConditions.elementToBeClickable(createNewApproverGroupBtn)).click();
        nameField.click();
        nameField.sendKeys(name);
        approversDropdwn.click();
        approver1 = firstApprover.getText();
        firstApprover.click();
        pressEscape();
        activeApproverGroupCheckbox.click();
        createBtn.click();
        goToLastPage();
        wait.until(ExpectedConditions.textToBePresentInElement(lastApproverGroup,name));
        return new ApproverGroupsPage(driver);
    }

    public void editApproverGroup() throws AWTException {
        js = (JavascriptExecutor) driver;
        newName = generateRandomName();
        js.executeScript("arguments[0].scrollIntoView(true);", editIcon);
        js.executeScript("arguments[0].click();", editIcon);
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).click();
        nameField.click();
        nameField.clear();
        nameField.sendKeys(newName);
        approversDropdwn.click();
        approver2 = secondApprover.getText();
        secondApprover.click();
        pressEscape();
        wait.until(ExpectedConditions.elementToBeClickable(activeApproverGroupCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
        wait.until(ExpectedConditions.textToBePresentInElement(lastApproverGroup,newName));
    }


}
