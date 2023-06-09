package org.selenium.pom.pages.admin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.awt.*;

public class TesterGroupsPage extends BasePage {
    JavascriptExecutor js;

    public String name;
    public String newName;

    public String checkboxStatus;

    public TesterGroupsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/admin']")
    WebElement adminBtn;
    @FindBy(xpath = "//*[text()='Tester Groups']")
    WebElement testerGroupsBtn;
    @FindBy(xpath = "//*[text()=' Create New Tester Group ']")
    WebElement createNewTesterGroupBtn;
    @FindBy(xpath = "//input[@name='name']")
    WebElement nameField;
    @FindBy(xpath = "//*[@role='combobox']")
    WebElement usersDropdwn;
    @FindBy(css = "span.mat-checkbox-inner-container")
    WebElement activeTesterGroupCheckbox;
    @FindBy(xpath = "//*[text()=\" Create \"]")
    WebElement createBtn;
    @FindBy(xpath = "//*[text()=\" Edit \"]")
    WebElement editBtn;
    @FindBy(xpath = "(//*[@class='mat-option-text'])[1]")
    WebElement firstTester;
    @FindBy(xpath = "(//*[@class='mat-option-text'])[2]")
    WebElement secondTester;
    @FindBy(xpath = "(//span[contains(text(),\"edit\")])[last()]")
    WebElement editIcon;
    @FindBy(xpath = "(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[last()]")
    WebElement lastTesterGroup;
    @FindBy(xpath = "(//td[@class=\"mat-cell cdk-cell cdk-column-users mat-column-users ng-star-inserted\"])[last()]")
    WebElement lastEmail;

    public String tester1;
    public String tester2;

    public ApproverGroupsPage createNewTesterGroup() throws AWTException {
        wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
        clickTabArrow();
        wait.until(ExpectedConditions.elementToBeClickable(testerGroupsBtn)).click();
        name = generateRandomName();
        wait.until(ExpectedConditions.elementToBeClickable(createNewTesterGroupBtn)).click();
        nameField.click();
        nameField.sendKeys(name);
        usersDropdwn.click();
        firstTester.click();
        wait.until(ExpectedConditions.textToBePresentInElement(firstTester, "."));
        tester1 = firstTester.getText();
        pressEscape();
        activeTesterGroupCheckbox.click();
        createBtn.click();
        goToLastPage();
        wait.until(ExpectedConditions.textToBePresentInElement(lastTesterGroup, name));
        return new ApproverGroupsPage(driver);
    }

    public void editTesterGroup() throws AWTException {
        js = (JavascriptExecutor) driver;
        newName = generateRandomName();
        js.executeScript("arguments[0].scrollIntoView(true);", editIcon);
        js.executeScript("arguments[0].click();", editIcon);
        usersDropdwn.click();
        secondTester.click();
        wait.until(ExpectedConditions.textToBePresentInElement(secondTester, "."));
        tester2 = secondTester.getText();
        pressEscape();
        wait.until(ExpectedConditions.elementToBeClickable(activeTesterGroupCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
        wait.until(ExpectedConditions.textToBePresentInElement(lastTesterGroup, name));
        wait.until(ExpectedConditions.textToBePresentInElement(lastEmail,tester2));
    }


}
