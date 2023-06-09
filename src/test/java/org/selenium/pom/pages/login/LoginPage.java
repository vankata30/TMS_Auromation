package org.selenium.pom.pages.login;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.base.ExplicitWait;
import org.selenium.pom.base.TOTPGenerator;

import java.time.Duration;
import java.util.Set;


    public class LoginPage extends BasePage {
//        WebDriver driver;
        public LoginPage(WebDriver driver){
            super(driver);
            this.driver=driver;
            PageFactory.initElements(driver, this);
        }
        @FindBy(id = "i0116")
        public WebElement emailField;
        @FindBy(id = "idSIButton9")
        public WebElement nextBtn;
        @FindBy(id = "i0118")
        WebElement passwordField;
        @FindBy(id = "idSIButton9")
        public WebElement signInBtn;
        @FindBy(id = "signInAnotherWay")
        WebElement signInAnotherWayBtn;
        @FindBy(xpath = "//*[text()=\"Use a verification code\"]")
        WebElement verificationCodeLink;
        @FindBy(id = "idTxtBx_SAOTCC_OTC")
        WebElement verificationCodeFld;
        @FindBy(id = "idSubmit_SAOTCC_Continue")
        WebElement continueBtn;
        String url = "https://templating-dev.multichoice.com/home";
        String email = "ivan.petro@multichoice.co.za";
        String pass = "Ippac2023!";


        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);


        public void login() throws Throwable{
            driver.get(url);
            ExplicitWait ew = new ExplicitWait(driver);
            Thread.sleep(200);
            ew.findElement(By.id("i0116")).sendKeys("ivan.petro@multichoice.co.za");
            Thread.sleep(400);
            ew.findElement(By.id("idSIButton9")).click();
            ew.findElement(By.id("i0118")).sendKeys("Ippac2022!");
            Thread.sleep(3000);
            ew.findElement(By.xpath("//*[@type='submit']")).click();
            ew.findElement(By.id("signInAnotherWay")).click();
            ew.findElement(By.xpath("//*[text()=\"Use a verification code\"]")).click();

            ew.findElement(By.id("idTxtBx_SAOTCC_OTC")).sendKeys(TOTPGenerator.getTwoFactorCode());
            Thread.sleep(200);
            ew.findElement(By.id("idSubmit_SAOTCC_Continue")).click();
            ew.findElement(By.id("idSIButton9")).click();


//           return driver.manage().getCookies();
            //        for (Cookie cookie : cookies){
            //            driver.manage().addCookie(cookie);
            //        }
        }
    }

