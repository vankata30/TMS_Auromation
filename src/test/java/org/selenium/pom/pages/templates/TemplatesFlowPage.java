package org.selenium.pom.pages.templates;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.base.CustomExpectedConditions;

import java.awt.*;
import java.time.Duration;

public class TemplatesFlowPage extends BasePage {
    JavascriptExecutor js;

    public TemplatesFlowPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    TemplatesPage tp;
    @FindBy(xpath = "(//*[contains(@class,'mat-form-field-autofill-control')])[2]")
    WebElement channelType;
    @FindBy(xpath = "//*[@data-tooltip='Blocks manager']")
    WebElement blocksManager;
    @FindBy(xpath = "(//*[@title='Inline text'])[1]")
    WebElement inlineText;
    @FindBy(xpath = "(//*[@data-gjs-type='text'])[1]")
    WebElement textField;
    @FindBy(xpath = "//body[@class='gjs-dashed']")
    WebElement target;
    @FindBy(xpath = "//span[contains(text(),  ' Reject ')]")
    public WebElement rejectBtn;
    @FindBy(xpath = "//span[contains(text(),  ' Approve ')]")
    WebElement approveBtn;
    @FindBy(xpath = "//span[contains(text(),'Go To Request Details')]")
    WebElement goToRequestDetailsBtn;
    @FindBy(xpath = "(//*[contains(@class,'mat-form-field-autofill-control')])[3]")
    WebElement status;
    @FindBy(xpath = "//span[contains(text(),  'Confirm')]")
    WebElement confirmBtn;
    @FindBy(xpath = "//span[contains(text(),  'Confirm')]")
    WebElement currentStatus;
    @FindBy(xpath = "//span[contains(text(),  ' Publish ')]")
    WebElement publishBtn;
    @FindBy(xpath = "//span[contains(text(),'Go To Template Details')]")
    WebElement goToTemplateDetailsBtn;
    @FindBy(xpath = "//span[contains(text(),'Archive')]")
    WebElement archiveBtn;

    public boolean sendForTesting() throws AWTException, InterruptedException {
        tp = new TemplatesPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(tp.sendForTestingBtn)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(@class,'cdk-overlay-backdrop')])")));
        Thread.sleep(2300);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#testerUsers")))).click();
//        wait.until(ExpectedConditions.elementToBeClickable(tp.testerGroupsDrpdwn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(tp.firstOption)).click();
        pressEscape();
        wait.until(ExpectedConditions.elementToBeClickable(tp.continueBtn)).click();
        wait.until(ExpectedConditions.urlContains("/testing"));
        return driver.getCurrentUrl().contains("/testing");
    }

    public void buildTemplate() throws AWTException {
        tp = new TemplatesPage(driver);
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        tp.openTemplate("IN PROGRESS");
        js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.elementToBeClickable(tp.buildBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(tp.templateBuilder));
        String channel = channelType.getAttribute("value");
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(tp.iframe));
//        driver.switchTo().frame(tp.iframe);
        Actions a = new Actions(driver);
        if (channel.equals("SMS")) {
            driver.switchTo().frame(tp.iframe);
//            js.executeScript("arguments[0].scrollIntoView(true);", tp.builderInputField);
//            js.executeScript("arguments[0].click();", tp.builderInputField);
//            js.executeScript("arguments[0].click();", tp.builderInputField);
            a.doubleClick(tp.builderInputField).build().perform();
//            a.doubleClick(tp.builderInputField).perform();
            tp.builderInputField.clear();
            sendKeysLetterByLetter(tp.builderInputField, "test");
            wait.until(ExpectedConditions.textToBePresentInElement(tp.builderInputField, "test"));
            driver.findElement(By.xpath("//*[@data-gjs-type='SMS']")).click();
            driver.switchTo().defaultContent();
        } else if (channel.equals("Email")) {
            driver.findElement(By.cssSelector(".gjs-layer-vis.fa.fa-eye")).click();
//
        }else if (channel.equals("App Push")){
            driver.switchTo().frame(tp.iframe);
//            js.executeScript("arguments[0].scrollIntoView(true);", tp.builderInputField);
//            js.executeScript("arguments[0].click();", tp.builderInputField);
//            js.executeScript("arguments[0].click();", tp.builderInputField);
            a.doubleClick(tp.builderInputField).build().perform();
//            a.doubleClick(tp.builderInputField).perform();
            tp.builderInputField.clear();
            sendKeysLetterByLetter(tp.builderInputField, "test");
            wait.until(ExpectedConditions.textToBePresentInElement(tp.builderInputField, "test"));
            driver.findElement(By.xpath("//*[@data-gjs-type='App Push']")).click();
            driver.switchTo().defaultContent();
        }
//        js.executeScript("arguments[0].value = arguments[1];", tp.builderInputField, "texxt");
//        wait.until(ExpectedConditions.elementToBeClickable(tp.goBackBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(tp.saveBtn)).click();
    }

    public boolean completeMessageIsDisplayed;
    public boolean statusIsChanged;

    public void completeTesting() {
//        wait.until(ExpectedConditions.elementToBeClickable(tp.goToTemplateTestingBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(tp.h2));
        if (!tp.h2.getText().contains("dedicated")) {
            driver.navigate().refresh();
        }
        wait.until(ExpectedConditions.elementToBeClickable(tp.completeTestingBtn)).click();
//        wait.until(ExpectedConditions.invisibilityOf(overlay));
//        wait.until(ExpectedConditions.elementToBeClickable(completeTestingBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(tp.confirmBtn)).click();
        wait.until(ExpectedConditions.textToBePresentInElement(tp.h2, "has been completed"));
        String completedMessage = driver.findElement(By.xpath("(//h2)[1]")).getText();
        wait.until(ExpectedConditions.elementToBeClickable(tp.goToTemplateDetailsBtn)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(tp.status));
        String newStatus = tp.status.getAttribute("value");
        System.out.println(completedMessage);
        wait.until(ExpectedConditions.attributeContains(tp.status, "value", "COMPLETED"));
        if (completedMessage.equals("The testing for this Template has been completed.")) {
            completeMessageIsDisplayed = true;
        }
        if (newStatus.contains("COMPLETED TESTING")) {
            statusIsChanged = true;
        }

    }

    public boolean rejectButtonIsVisible = true;
    public boolean approveButtonIsVisible = true;
    public boolean publishButtonIsVisible = false;
    public boolean archiveButtonIsVisible = false;


    public String reject() throws AWTException {
//        openTemplate("COMPLETED TESTING");
        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(rejectBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
        wait.until(ExpectedConditions.invisibilityOf(rejectBtn));

        try {
            WebElement btn = driver.findElement(By.xpath("//span[contains(text(),  ' Reject ')]"));
        } catch (NoSuchElementException e) {
            rejectButtonIsVisible = false;
            System.out.println("Reject button is not displayed");
        }
        wait.until(ExpectedConditions.attributeToBe(status, "value", "REJECTED"));
        return status.getAttribute("value");
    }

    public String approve() {
        wait.until(ExpectedConditions.elementToBeClickable(approveBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
        wait.until(ExpectedConditions.invisibilityOf(approveBtn));
        try {
            WebElement btn = driver.findElement(By.xpath("//span[contains(text(),  ' Approve ')]"));
        } catch (NoSuchElementException e) {
            approveButtonIsVisible = false;
            System.out.println("Approve button is not displayed");
        }
        wait.until(ExpectedConditions.elementToBeClickable(publishBtn));
        publishButtonIsVisible = publishBtn.isDisplayed();
        wait.until(ExpectedConditions.attributeToBe(status, "value", "APPROVED"));
        return status.getAttribute("value");
    }

    public String publish() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(publishBtn)).click();
        //this is just temporary until the confirm dialog is fixed!!!!
//        wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),  'Publish')]")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(@class,'publish-btn')]")))).click();
//        driver.findElement(By.xpath("(//span[contains(text(),  'Publish')])[2]")).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(@class,'cdk-overlay-backdrop')])")));
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[contains(text(),  'Publish')]")))).click();
        // wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
        wait.until(ExpectedConditions.invisibilityOf(publishBtn));
        try {
            WebElement btn = driver.findElement(By.xpath("//span[contains(text(),  ' Publish ')]"));
        } catch (NoSuchElementException e) {
            publishButtonIsVisible = false;
            System.out.println("Publish button is not displayed");
        }
        wait.until(ExpectedConditions.attributeToBe(status, "value", "COMPLETED"));
        String stat = status.getAttribute("value");
        wait.until(ExpectedConditions.elementToBeClickable(goToTemplateDetailsBtn)).click();
        return stat;
    }

    public String archive() {
        tp = new TemplatesPage(driver);
//        driver.get(urlBeforeCopy);
        wait.until(ExpectedConditions.elementToBeClickable(archiveBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
        wait.until(ExpectedConditions.invisibilityOf(archiveBtn));

        try {
            WebElement btn = driver.findElement(By.xpath("//span[contains(text(),  ' Archive ')]"));
        } catch (NoSuchElementException e) {
            archiveButtonIsVisible = false;
            System.out.println("Archive button is not displayed");
        }
        wait.until(ExpectedConditions.attributeToBe(status, "value", "ARCHIVED"));
        return status.getAttribute("value");
    }

    String urlBeforeCopy;
    public String afterCopyStatus;

    public void copyTemplate() throws AWTException {
        js = (JavascriptExecutor) driver;
        tp = new TemplatesPage(driver);
        tp.getTemplateOverviewDetails();
        urlBeforeCopy = driver.getCurrentUrl();
        tp.beforeCopy = tp.templateOverviewValues;
        wait.until(ExpectedConditions.elementToBeClickable(tp.copyBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(tp.approversDrpdwn));
        tp.approversDrpdwn.click();
//        js.executeScript("arguments[0].click();", approversDrpdwn);
        wait.until(ExpectedConditions.elementToBeClickable(tp.firstApprover)).click();
        wait.until(ExpectedConditions.attributeContains(By.xpath("(//mat-option)[1]"),
                "aria-selected","true"));
        pressEscape();
        String creator = wait.until(ExpectedConditions.elementToBeClickable(tp.templateCreatorDrpdwn)).getText();
        wait.until(ExpectedConditions.elementToBeClickable(tp.createBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn));
        js.executeScript("arguments[0].click();", goToRequestDetailsBtn);
//        wait.until(ExpectedConditions.elementToBeClickable(goToTemplateDetailsBtn));
//        js.executeScript("arguments[0].click();", goToTemplateDetailsBtn);
//        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(tp.buildBtn));
        tp.getTemplateOverviewDetails();

        tp.afterCopy = tp.templateOverviewValues;
        afterCopyStatus = status.getAttribute("value");
        driver.get(urlBeforeCopy);
    }

//    ExpectedCondition<Boolean> elementToBeClickable = new ExpectedCondition<Boolean>() {
//        @Override
//        public Boolean apply(WebDriver driver) {
//            return null;
//        }
//
//        public Boolean apply(WebDriver driver, By by) {
//            try {
//                WebElement element = driver.findElement(by);
//                return element.isEnabled() && element.isDisplayed();
//            } catch (ElementClickInterceptedException e) {
//                return false;
//            }
//        }
//    };


}
