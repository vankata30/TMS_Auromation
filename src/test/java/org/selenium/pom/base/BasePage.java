package org.selenium.pom.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class BasePage {
    Robot robot;
    public WebDriver driver;
    public Wait<WebDriver> wait;
    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    public String clickIsActiveCheckbox(WebElement element) {
        element.click();
        if (element.getAttribute("class").toString().contains("checked")) {
            return "true";
        } else {
            return "false";
        }
    }
    public void goToLastPage(){
        js = (JavascriptExecutor) driver;
        String arrowClass = nextPageArrow.getAttribute("class");
        wait.until(ExpectedConditions.elementToBeClickable(nextPageArrow));
        while (!arrowClass.contains("mat-button-disabled")) {
            js.executeScript("arguments[0].click();", nextPageArrow);
            arrowClass = nextPageArrow.getAttribute("class");
        }
    }

    public String generateRandomName() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public String generateRandomCode() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

    public int findElementOnPage(String name) {
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
            By locator = By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[" + i + "]");
            if (driver.findElement(locator).getText().equals(name)) {
                found = true;
                rowNum = i;
            }
        }
        return rowNum;

    }
    public void clickTabArrow() {
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
//        wait.until(ExpectedConditions.visibilityOf(tabArrow));
        if (driver.findElement(By.xpath("//button[@type='button'][2]")).isDisplayed()) {
            WebElement tabArrow = driver.findElement(By.xpath("//button[@type='button'][2]"));
            while (!tabArrow.getAttribute("class").contains("disabled")) {
                tabArrow.click();
            }
        }
    }
    public void pressEscape() throws AWTException {
        robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
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
            By locator = By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[" + i + "]");
            if (driver.findElement(locator).getText().equals(name)) {
                found = true;
                rowNum = i;
            }
        }
        By locator = By.xpath("(//td[@class=\"mat-cell cdk-cell cdk-column-userEmails mat-column-userEmails ng-star-inserted\"])[" + rowNum + "]");
        return driver.findElement(locator).getText();
    }
    public WebElement getEditIcon(int row) {
        By locator = By.xpath("(//span[contains(text(),\"edit\")])[" + row + "]");
        return driver.findElement(locator);
    }
    @FindBy(xpath = "//button[@aria-label=\"Next page\"]")
    WebElement nextPageArrow;
    public void sendKeysLetterByLetter(WebElement el, String keys) {
        el.click();
        for (int i = 0; i < keys.length(); i++) {
            el.sendKeys(String.valueOf(keys.charAt(i)));
        }
    }
}
