package org.selenium.pom.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.pages.login.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.CookieStore;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

public class BaseTest {
    public WebDriver driver;
    public WebDriver driver2;
    public LoginPage loginPage;
    JavascriptExecutor js;
    public Wait<WebDriver> wait;
    //    public WebDriver newDriver;
    FileOutputStream fos;
    ObjectOutputStream oos;
    FileInputStream fis;
    ObjectInputStream ois;

    //   public List<org.apache.hc.client5.http.cookie.Cookie> cookies;
    Set<Cookie> cookies;
    Set<Cookie> cookies2;

//    @BeforeSuite
//    public void Authenticate() throws Throwable {
////
//        driver2 = new DriverManager().initializeDriver();
//        wait = new FluentWait<WebDriver>(driver2)
//                .withTimeout(Duration.ofSeconds(30))
//                .pollingEvery(Duration.ofSeconds(5))
//                .ignoring(NoSuchElementException.class);
//        driver2.get("https://templating-dev.multichoice.com/");
//        loginPage = new LoginPage(driver2);
//
//
//        cookies = loginPage.login();
////
//        driver2.quit();
////        System.out.println(cookies);
//    }

    @BeforeClass
    public void startDriver() throws Throwable {
        driver = new DriverManager().initializeDriver();
        driver.get("https://login.microsoftonline.com/01ea1ee8-0c15-4160-9922-f383f39a19be/oauth2/v2.0/authorize?response_type=code&client_id=95bafde2-699f-4c31-9432-f3eee49c30d6&state=bDZ4TkJ1R2YwMFF0UGdKeElmT3FBLThlbXJIeWpReExHMVoxcXB5LTFvY1Bf&redirect_uri=https%3A%2F%2Ftemplating-dev.multichoice.com%2F&scope=openid%20profile%20email%20offline_access%20api%3A%2F%2F95bafde2-699f-4c31-9432-f3eee49c30d6%2Fbackend&code_challenge=KDAg-TQWMdiHCDRJWbB8xAjXwLp1eepAHjjGjuLlAUg&code_challenge_method=S256&nonce=bDZ4TkJ1R2YwMFF0UGdKeElmT3FBLThlbXJIeWpReExHMVoxcXB5LTFvY1Bf&sso_reload=true");
        loginPage = new LoginPage(driver);
        loginPage.login();
//
//        driver.manage().deleteAllCookies();
//        for (Cookie cookie : cookies) {
//            driver.manage().addCookie(cookie);
//        }
//        driver.navigate().refresh();
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("idSIButton9")))).click();
//        loginPage = new LoginPage(driver);
//        loginPage.login();
    }


    public int findElement(String name) {
        js = (JavascriptExecutor) driver;
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        int i = 0;
        boolean present = false;
        boolean found = false;
        int rowNum = 0;
        while (!found) {
            i++;
//            if (i == 11) {
//                nextPageArrow.click();
//                i = 1;
//            }
            By locator = By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[" + i + "]");
            if (driver.findElement(locator).getText().equals(name)) {
                found = true;
                rowNum = i;
            }
        }
        return rowNum;

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
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[" + i + "]"))));
            By locator = By.xpath("(//td[@class='mat-cell cdk-cell cdk-column-name mat-column-name ng-star-inserted'])[" + i + "]");
            if (driver.findElement(locator).getText().equals(name)) {
                found = true;
                rowNum = i;
            }
        }
        return rowNum;

    }

    public String getStatus(String name) {
        int rowNum = findElement(name);
        By locator = By.xpath("(//td[@class=\"mat-cell cdk-cell cdk-column-isActive mat-column-isActive ng-star-inserted\"])[" + rowNum + "]");
        return driver.findElement(locator).getText();
    }

    public String getUserEmail(String name) {
        int rowNum = findElement(name);
        By locator = By.xpath("(//td[@class=\"mat-cell cdk-cell cdk-column-userEmails mat-column-userEmails ng-star-inserted\"])[" + rowNum + "]");
        return driver.findElement(locator).getText();
    }

    public String getUser(String name) {
        int rowNum = findElement(name);
        By locator = By.xpath("(//td[@class=\"mat-cell cdk-cell cdk-column-users mat-column-users ng-star-inserted\"])[" + rowNum + "]");
        return driver.findElement(locator).getText();
    }


//    @AfterClass
//    public void quitDriver(){
//        driver.quit();
//    }
}
