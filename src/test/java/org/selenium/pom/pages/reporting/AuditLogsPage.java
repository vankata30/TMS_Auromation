package org.selenium.pom.pages.reporting;

import io.cucumber.java.et.Ja;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.base.ExcelFileReader;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class AuditLogsPage extends BasePage {
    public AuditLogsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    JavascriptExecutor js;
    ExcelFileReader reader;

     String testUserEmail = "Ivan.Petro@multichoice.co.za";
    @FindBy(xpath = "//a[@href='/reporting']")
     WebElement reportingBtn;
    @FindBy(xpath = "//*[text()='Audit Logs']")
     WebElement auditLogsBtn;
    @FindBy(xpath = "//*[contains(@id,'expansion-panel')]")
    WebElement filterMenu;
    @FindBy(xpath = "(//mat-datepicker-toggle)[1]")
     WebElement fromDatePicker;
    @FindBy(xpath = "(//mat-datepicker-toggle)[3]")
     WebElement fromDatePickerExport;
    @FindBy(xpath = "(//mat-datepicker-toggle)[2]")
     WebElement toDatePicker;
    @FindBy(xpath = "(//mat-datepicker-toggle)[4]")
     WebElement toDatePickerExport;
    @FindBy(xpath = "//*[@class='mat-calendar-body-cell-content mat-focus-indicator' and contains(text(),'12')]")
     WebElement date12;
    @FindBy(xpath = "//*[@class='mat-calendar-body-cell-content mat-focus-indicator' and contains(text(),'15')]")
     WebElement date15;
    @FindBy(name = "userEmail")
     WebElement userEmailFilter;
    @FindBy(xpath = "//*[@aria-label='Previous month']")
     WebElement previousMonthArrow;
    @FindBy(xpath = "//*[contains(text() , 'Export To Excel')]")
     WebElement exportToExcelBtn;
    @FindBy(xpath = "//*[normalize-space(text()) = 'Export']")
     WebElement exportBtn;
    @FindBy(xpath = "//div[@class='mat-paginator-range-label']")
     WebElement paginationText;
    @FindBy(xpath = "//button[@aria-label=\"Next page\"]")
     WebElement nextPageArrow;

    public int exportToExcel() throws IOException {
        js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.elementToBeClickable(reportingBtn)).click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(reportingBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(filterMenu)).click();
        filterMenu = driver.findElement(By.xpath("//*[contains(@id,'expansion-panel')]"));
//        wait.until(ExpectedConditions.elementToBeClickable(filterMenu));
//        wait.until(ExpectedConditions.stalenessOf(filterMenu));
//        wait.until(ExpectedConditions.refreshed(
//                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id,'expansion-panel')]"))
//        ));
//        while (filterMenu.getAttribute("aria-disabled").equals("false")){
//
//            js.executeScript("arguments[0].click();", filterMenu);
//        }
//        filterMenu.click();
//        try {
//            WebElement filter = driver.findElement(By.xpath("//*[contains(@id,'expansion-panel')]"));
//            js.executeScript("arguments[0].click();", filter);
//        }
//        catch(org.openqa.selenium.StaleElementReferenceException ex)
//        {
//            WebElement filter = driver.findElement(By.xpath("//*[contains(@id,'expansion-panel')]"));
//            js.executeScript("arguments[0].click();", filter);
//        }
//        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[contains(@class,'mat-expansion-indicator')]")));
        reader = new ExcelFileReader();
        chooseDateRange();
        exportToExcelBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(fromDatePicker));
        chooseExportDateRange();
        exportBtn.click();
        return reader.getRowCount(driver);
    }

    public int getAuditsNumber(){
        js = (JavascriptExecutor) driver;
        String arrowClass = nextPageArrow.getAttribute("class");
        int i = 0;
        while (!arrowClass.contains("mat-button-disabled")) {
            i += 10;
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextPageArrow);
            wait.until(ExpectedConditions.elementToBeClickable(nextPageArrow));
            js.executeScript("arguments[0].click();",nextPageArrow);
            arrowClass = nextPageArrow.getAttribute("class");
        }
        List<WebElement> list = driver.findElements(By.xpath("//tr[@class='mat-row cdk-row ng-star-inserted']"));
        i += list.size();
        return i;
    }
    public void chooseDateRange() {
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.presenceOfElementLocated(By.xpath("(//mat-datepicker-toggle)[1]"))
        ));
        wait.until(ExpectedConditions.elementToBeClickable(fromDatePicker)).click();
        previousMonthArrow.click();
        date12.click();
        toDatePicker.click();
        previousMonthArrow.click();
        date15.click();
    }

    public void chooseExportDateRange() {
        fromDatePickerExport.click();
        previousMonthArrow.click();
        date12.click();
        toDatePickerExport.click();
        previousMonthArrow.click();
        date15.click();
    }
}
