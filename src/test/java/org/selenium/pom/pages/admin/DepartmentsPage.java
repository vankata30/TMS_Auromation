package org.selenium.pom.pages.admin;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class DepartmentsPage extends BasePage {
    JavascriptExecutor js;

    public DepartmentsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String name;
    public String newName;
    public String code;
    public String checkboxStatus;
    @FindBy(xpath = "//a[@href='/admin']")
    WebElement adminBtn;
    @FindBy(id = "mat-tab-label-4-0")
    WebElement departmentsBtn;
    @FindBy(xpath = "//*[contains(text(),\"Create New Department\")]")
    WebElement createNewDepartmentBtn;
    @FindBy(xpath = "(//span[contains(text(),\"edit\")])[last()]")
    WebElement editIcon;
    @FindBy(xpath = "//input[@name='name']")
    WebElement nameField;
    @FindBy(xpath = "//input[@name='code']")
    WebElement codeField;
    @FindBy(css = "span.mat-checkbox-inner-container")
    WebElement activeDeptCheckbox;
    @FindBy(xpath = "//*[text()=\" Create \"]")
    WebElement createBtn;
    @FindBy(xpath = "//*[text()=\" Edit \"]")
    WebElement editBtn;
    @FindBy(xpath = "(//*[@class='mat-cell cdk-cell cdk-column-isActive mat-column-isActive ng-star-inserted'])[last()]")
    WebElement lastDepartmentStatus;
    @FindBy(xpath = "(//*[@class=\"mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted\"])[last()]")
    WebElement lastDepartmentName;
    @FindBy(xpath = "(//*[@class=\"mat-cell cdk-cell cdk-column-code mat-column-code ng-star-inserted\"])[last()]")
    WebElement lastDepartmentCode;
    @FindBy(xpath = "//button[@aria-label=\"Next page\"]")
    WebElement nextPageArrow;
    @FindBy (className = "overlay")
    public WebElement overlay;


    public void goToLastPage(){
        js = (JavascriptExecutor) driver;
        String arrowClass = nextPageArrow.getAttribute("class");
        wait.until(ExpectedConditions.elementToBeClickable(nextPageArrow));
        while (!arrowClass.contains("mat-button-disabled")) {
            js.executeScript("arguments[0].click();", nextPageArrow);
            arrowClass = nextPageArrow.getAttribute("class");
        }
    }

    public DepartmentsPage createNewDepartment() {
        wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
        name = generateRandomName();
        wait.until(ExpectedConditions.elementToBeClickable(createNewDepartmentBtn)).click();
        nameField.click();
        nameField.sendKeys(name);
        activeDeptCheckbox.click();
        createBtn.click();
        return new DepartmentsPage(driver);
    }

    public void editDepartment() {
        js = (JavascriptExecutor) driver;
        newName = generateRandomName();
        code = generateRandomCode();
        js.executeScript("arguments[0].scrollIntoView(true);", editIcon);
        js.executeScript("arguments[0].click();", editIcon);
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).click();
        nameField.click();
        nameField.clear();
        nameField.sendKeys(newName);
        codeField.sendKeys(code);
        checkboxStatus = clickIsActiveCheckbox(activeDeptCheckbox);
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(lastDepartmentName));
    }

    public String getNewDepartmentName() {
        wait.until(ExpectedConditions.textToBePresentInElement(lastDepartmentName, name));
        return lastDepartmentName.getText();
    }

    public String getNewDepartmentNewName() {
        wait.until(ExpectedConditions.textToBePresentInElement(lastDepartmentName, newName));
        return lastDepartmentName.getText();
    }

    public String getNewDepartmentCode() {
        return lastDepartmentCode.getText();
    }

    public String getNewDepartmentStatus() {
        return lastDepartmentStatus.getText();
    }
}
