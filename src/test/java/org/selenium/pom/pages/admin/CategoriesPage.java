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

import java.awt.*;

public class CategoriesPage extends BasePage {
    WebDriver driver;
    JavascriptExecutor js;

    public CategoriesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/admin']")
    WebElement adminBtn;
    @FindBy(xpath = "//*[text()='Categories']")
    WebElement categoriesBtn;
    @FindBy(xpath = "//*[text()=' Create New Category ']")
    WebElement createNewCategoryBtn;
    @FindBy(xpath = "//input[@name='categoryName']")
    WebElement nameField;
    @FindBy(xpath = "//input[@name='subcategories']")
    WebElement subcategoryField;
    @FindBy(xpath = "//span[text()='Add Subcategory']")
    WebElement addSubcategoryBtn;
    @FindBy(xpath = "//span[text()=' Create ']")
    WebElement createBtn;
    @FindBy(xpath = "//span[text()=' Edit ']")
    WebElement editBtn;
    @FindBy(xpath = "(//span[contains(text(),\"edit\")])[last()]")
    WebElement editIcon;
    @FindBy(xpath = "//button[@aria-label=\"Next page\"]")
    WebElement nextPageArrow;


    public String getSubcategories(String name) throws Throwable {
        js = (JavascriptExecutor) driver;
        int i = 0;
        boolean present = false;
        boolean found = false;
        int rowNum = 0;
        while (!found) {
            i++;
            if (i == 11) {
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@aria-label=\"Next page\"]")));
                wait.until(ExpectedConditions.elementToBeClickable(editIcon));
//                driver.findElement(By.xpath("//button[@aria-label=\"Next page\"]")).click();
                i = 1;
                Thread.sleep(1111);
            }
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[" + i + "]"))));
            By locator = By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[" + i + "]");
            if (driver.findElement(locator).getText().equals(name)) {
                found = true;
                rowNum = i;
            }
        }
        By locator = By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-subcategories mat-column-subcategories ng-star-inserted'])[" + rowNum + "]");
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(locator),subcategoryName));
        return driver.findElement(locator).getText();
    }
    public String getNewSubcategories(String name) throws Throwable {
        js = (JavascriptExecutor) driver;
        int i = 0;
        boolean present = false;
        boolean found = false;
        int rowNum = 0;
        while (!found) {
            i++;
            if (i == 11) {
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@aria-label=\"Next page\"]")));
                wait.until(ExpectedConditions.elementToBeClickable(editIcon));
//                driver.findElement(By.xpath("//button[@aria-label=\"Next page\"]")).click();
                i = 1;
                Thread.sleep(1111);
            }
            By locator = By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[" + i + "]");
            if (driver.findElement(locator).getText().equals(name)) {
                found = true;
                rowNum = i;
            }
        }
        By sublocator = By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-subcategories mat-column-subcategories ng-star-inserted'])[" + rowNum + "]");
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(sublocator),newSubcategory));
        return driver.findElement(sublocator).getText();
    }
    public CategoriesPage createNewCategory(){
        wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(categoriesBtn)).click();
        categoryName = generateRandomName();
        subcategoryName = generateRandomName();
        wait.until(ExpectedConditions.elementToBeClickable(createNewCategoryBtn)).click();
        nameField.click();
        nameField.sendKeys(categoryName);
        subcategoryField.click();
        subcategoryField.sendKeys(subcategoryName);
        addSubcategoryBtn.click();
        createBtn.click();
        return new CategoriesPage(driver);
    }
    public void editCategory(){
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", editIcon);
        js.executeScript("arguments[0].click();", editIcon);
        newSubcategory = generateRandomName();
        subcategoryField.click();
        subcategoryField.sendKeys(newSubcategory);
        addSubcategoryBtn.click();
        editBtn.click();
    }
    public String categoryName;
    public String subcategoryName;
    public String newSubcategory;

}
