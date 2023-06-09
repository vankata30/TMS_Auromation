package org.selenium.pom.pages.admin;

import com.thoughtworks.qdox.model.expression.ExclusiveOr;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.testng.Assert;

import java.awt.*;

public class UsersPage extends BasePage {

    JavascriptExecutor js;

    public UsersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/admin']")
    WebElement adminBtn;
    @FindBy(xpath = "//*[text()='Users']")
    WebElement usersBtn;
    @FindBy(xpath = "//*[text()=\" Save \"]")
    WebElement saveBtn;
    @FindBy(id = "countries")
    WebElement countriesDrpdwn;
    @FindBy(id = "departments")
    WebElement departmentsDrpdwn;
    @FindBy(xpath = "(//*[@class='mat-option-text'])[1]")
    WebElement firstCountry;
    @FindBy(xpath = "(//*[@class='mat-option-text'])[1]")
    WebElement firstDept;
    @FindBy(xpath = "//*[contains(@id,'expansion-panel')]")
    WebElement filterMenu;
    @FindBy(name = "email")
    WebElement emailFilter;
    @FindBy(name = "username")
    WebElement usernameFilter;
    @FindBy(xpath = "//*[@role='combobox'][1]")
    WebElement roleDrpdwn;
    @FindBy(xpath = "//mat-label[text()='Country']")
    WebElement countryDrpdwn;
    @FindBy(xpath = "//mat-label[text()='Department']")
    WebElement departmentDrpdwn;
    @FindBy(xpath = "//*[text()=' Template.Creator ']")
    WebElement templateCreatorOption;

    public String testUserEmail = "Ivan.Petro@multichoice.co.za";
    public String testUsername = "Ivan Petro";
    public int row;
    public String country;
    public boolean firstCountrySelected;
    public boolean firstDepartmentSelected;
    public String department;
    @FindBy(xpath = "//h1")
    WebElement header;


    public void editUser() throws AWTException {
        js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
        clickTabArrow();
        wait.until(ExpectedConditions.elementToBeClickable(usersBtn)).click();
        row = findUser(testUserEmail);
        getEditIcon(row).click();
        wait.until(ExpectedConditions.elementToBeClickable(countriesDrpdwn)).click();
        country = firstCountry.getText();
        wait.until(ExpectedConditions.elementToBeClickable(firstCountry));
        firstCountrySelected = clickFirstCheckbox(firstCountry);
        new Actions(driver)
                .moveToElement(usersBtn, 8, 0)
                .perform();
//        pressEscape();
//        new Actions(driver)
//                .moveByOffset(13, 15)
//                .perform();
        Actions action = new Actions(driver);
        pressEscape();
//        js.executeScript("document.getElementsByClassName('cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing')[0].focus();");
//        wait.until(ExpectedConditions.elementToBeClickable(departmentDrpdwn)).click();
        js.executeScript("arguments[0].click();", departmentsDrpdwn);
//        departmentsDrpdwn.click();
        department = firstDept.getText();
        wait.until(ExpectedConditions.elementToBeClickable(firstDept));
        firstDepartmentSelected = clickFirstCheckbox(firstDept);
        new Actions(driver)
                .moveToElement(usersBtn, 8, 0)
                .perform();
        pressEscape();
        js.executeScript("arguments[0].click();", departmentsDrpdwn);
        row = findUser(testUserEmail);
    }

    public void filterByEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
        clickTabArrow();
        wait.until(ExpectedConditions.elementToBeClickable(usersBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(filterMenu));
        filterMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(emailFilter)).click();
        emailFilter.sendKeys(testUserEmail);
        row = findUser(testUserEmail);

    }

    public boolean clickFirstCheckbox(WebElement el) {
        WebElement element = driver.findElement(By.xpath("(//*[@class='mat-option-text'])[1]/preceding::*[1]"));
        boolean bool = element.getAttribute("class").toString().contains("checked");
        el.click();
        return !bool;

    }

    public WebElement getCountries(int row) {
        By locator = By.xpath("(//td[@class='mat-cell cdk-cell mat-tooltip-trigger country-columns cdk-column-countries mat-column-countries ng-star-inserted'])[" + row + "]");
        return driver.findElement(locator);
    }

    public WebElement getDepartments(int row) {
        By locator = By.xpath("(//td[@class='mat-cell cdk-cell mat-tooltip-trigger department-columns cdk-column-departments mat-column-departments ng-star-inserted'])[\" + row + \"]");
        return driver.findElement(locator);
    }

    public int findUser(String name) {
        js = (JavascriptExecutor) driver;
        int i = 0;
        boolean present = false;
        boolean found = false;
        int rowNum = 0;
        while (!found) {
            i++;
            if (i == 11) {
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@aria-label=\"Next page\"]")));
//                driver.findElement(By.xpath("//button[@aria-label=\"Next page\"]")).click();
                i = 1;
            }
            By locator = By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-email mat-column-email ng-star-inserted'])[" + i + "]");
            if (driver.findElement(locator).getText().equals(name)) {
                found = true;
                rowNum = i;
            }
        }
        return rowNum;

    }

    public String getUserEmail(String name) {
        js = (JavascriptExecutor) driver;
        int i = 0;
        boolean present = false;
        boolean found = false;
        int rowNum = 0;
        while (!found) {
            i++;
            if (i == 11) {
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@aria-label=\"Next page\"]")));
//                driver.findElement(By.xpath("//button[@aria-label=\"Next page\"]")).click();
                i = 1;
            }
            By locator = By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-userEmails mat-column-userEmails ng-star-inserted'])[" + i + "]");
            if (driver.findElement(locator).getText().equals(name)) {
                found = true;
                return driver.findElement(locator).getText();
            }
        }
        By locator = By.xpath("(//td[@class=\"mat-cell cdk-cell cdk-column-userEmails mat-column-userEmails ng-star-inserted\"])[" + rowNum + "]");
        return driver.findElement(locator).getText();
    }
}
