package org.selenium.pom.pages.templates;

import io.cucumber.java.an.E;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.selenium.pom.base.BasePage;

import javax.naming.BinaryRefAddr;
import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TemplatesPage extends BasePage {

    JavascriptExecutor js;

    public TemplatesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    @FindBy(xpath = "//*[contains(@id,'expansion-panel')]")
    WebElement filterMenu;
    @FindBy(xpath = "//*[@href='/templates/508']")
    WebElement el;
    @FindBy(xpath = "(//div[contains(@class, 'mat-form-field-infix')])[4]")
    WebElement statusDropdown;
    @FindBy(xpath = "//span[contains(text(),' IN PROGRESS ')]")
    WebElement inProgressOption;
    @FindBy(xpath = "//span[contains(text(),' ACTIVE ')]")
    WebElement activeOption;
    @FindBy(xpath = "//span[contains(text(),' TESTING ')]")
    WebElement testingOption;
    @FindBy(xpath = "//span[contains(text(),' COMPLETED TESTING ')]")
    WebElement completedOption;
    @FindBy(xpath = "//iframe")
    WebElement iframe;
    @FindBy(xpath = "(//*[@role='row'])[2]")
    public WebElement firstResult;
    @FindBy(xpath = "(//*[@role='option'])[1]")
    public WebElement firstOption;
    @FindBy(xpath = "(//td[contains(@class,'column-name')])[1]")
    WebElement tableTemplateName;
    @FindBy(xpath = "(//td[contains(@class,'column-status')])[1]")
    WebElement tableTemplateStatus;
    @FindBy(xpath = "(//td[contains(@class,'column-user')])[1]")
    WebElement tableTemplateUser;
    @FindBy(xpath = "(//td[contains(@class,'dateCreated')])[1]")
    WebElement tableTemplateDate;
    @FindBy(xpath = "(//td[contains(@class,'category')])[1]")
    WebElement tableTemplateCategory;
    @FindBy(xpath = "(//td[contains(@class,'channelType')])[1]")
    WebElement tableTemplateChannelType;
    @FindBy(xpath = "(//td[contains(@class,'version')])[1]")
    WebElement tableTemplateVersion;
    @FindBy(xpath = "(//*[contains(@class,'mat-form-field-autofill-control')])[1]")
    WebElement name;
    @FindBy(xpath = "(//*[contains(@class,'mat-form-field-autofill-control')])[2]")
    WebElement channelType;
    @FindBy(xpath = "(//*[contains(@class,'mat-form-field-autofill-control')])[3]")
    WebElement status;
    @FindBy(xpath = "(//*[contains(@class,'mat-form-field-autofill-control')])[4]")
    WebElement version;
    @FindBy(xpath = "(//*[contains(@class,'mat-form-field-autofill-control')])[6]")
    WebElement dateCreated;
    @FindBy(xpath = "(//*[contains(@class,'mat-form-field-autofill-control')])[6]")
    WebElement approvers;
    @FindBy(xpath = "(//*[contains(@class,'mat-form-field-autofill-control')])[8]")
    WebElement category;
    @FindBy(xpath = "(//*[contains(@class,'mat-form-field-autofill-control')])[7]")
    WebElement user;
    @FindBy(xpath = "//a[@href='/templates']")
    WebElement templatesBtn;
    @FindBy(xpath = "//a[@href='/testing']")
    WebElement testingBtn;
    @FindBy(xpath = "//span[contains(text(),'Clear Filter')]")
    WebElement clearFilterBtn;
    @FindBy(xpath = "//span[contains(text(),'Show Archived')]")
    WebElement showArchivedBtn;
    @FindBy(xpath = "//span[contains(text(),'Go To Request Details')]")
    WebElement goToRequestDetailsBtn;
    @FindBy(xpath = "//span[contains(text(),'Go To Template Testing')]")
    WebElement goToTemplateTestingBtn;
    @FindBy(xpath = "//span[contains(text(),'Complete Testing')]")
    WebElement completeTestingBtn;
    @FindBy(xpath = "//span[contains(text(),'Go To Template Details')]")
    WebElement goToTemplateDetailsBtn;
    @FindBy(xpath = "//span[contains(text(),'Copy')]")
    WebElement copyBtn;
    @FindBy(xpath = "//span[contains(text(),'Archive')]")
    WebElement archiveBtn;
    @FindBy(xpath = "//span[contains(text(),'Promote')]")
    WebElement promoteBtn;
    @FindBy(xpath = "//span[@class='mat-button-wrapper']")
    List<WebElement> displayedBtns;
    @FindBy(xpath = "//span[contains(text(),'Build')]")
    WebElement buildBtn;
    @FindBy(xpath = "//span[contains(text(),'Edit')]")
    WebElement editBtn;
    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continueBtn;
    @FindBy(xpath = "//span[contains(text(),'Go Back')]")
    WebElement goBackBtn;
    @FindBy(xpath = "//span[contains(text(),'Save')]")
    WebElement saveBtn;
    @FindBy(xpath = "//span[contains(text(),'Send For Testing')]")
    WebElement sendForTestingBtn;
    @FindBy(xpath = "//span[contains(text(),'Go To Template Testing')]")
    public WebElement goToTestingBtn;
    @FindBy(xpath = "(//*[contains(@class,'cdk-overlay-backdrop')])")
    WebElement overlay;
    @FindBy(css = "#approvers")
    WebElement approversDrpdwn;
    @FindBy(xpath = "(//span[@class='mat-option-text'])[1]")
    WebElement firstApprover;
    @FindBy(css = "#templateCreator")
    WebElement templateCreatorDrpdwn;
    @FindBy(css = "#testerGroups")
    WebElement testerGroupsDrpdwn;
    @FindBy(xpath = "//span[contains(text(), 'Create ')]")
    WebElement createBtn;
    @FindBy(xpath = "//span[contains(text(),  ' Confirm ')]")
    WebElement confirmBtn;
    @FindBy(xpath = "//span[contains(text(),  ' Send For Approval ')]")
    WebElement sendForApprovalBtn;
    @FindBy(xpath = "//span[contains(text(),  ' Reject ')]")
    public WebElement rejectBtn;
    @FindBy(xpath = "//span[contains(text(),  ' Approve ')]")
    WebElement approveBtn;
    @FindBy(xpath = "//app-template-builder")
    WebElement templateBuilder;
    @FindBy(xpath = "//p[@data-gjs-type='text']")
    WebElement builderInputField;
    @FindBy(name = "requestName")
    WebElement requestNameField;
    @FindBy(xpath = "(//h2)[1]")
    WebElement h2;



    List<String> initialValues = new ArrayList<>();
    List<String> templateOverviewValues = new ArrayList<>();

    public boolean compareValues() {
        boolean isTheSame = true;
        for (int i = 0; i < templateOverviewValues.size(); i++) {
            if (!templateOverviewValues.get(i).equals(initialValues.get(i))) {
                isTheSame = false;
            }
        }
        return isTheSame;
    }

    public boolean compareValues(List<String> list1, List<String> list2) {
        boolean isTheSame = true;
        for (int i = 0; i < list1.size(); i++) {
            if (i != 2 && i != 1 && i != 5) {
                if (!list1.get(i).equals(list2.get(i))) {
                    isTheSame = false;
                    break;
                }
            }
        }
        return isTheSame;
    }


    String templatesAddress = "https://templating-dev.multichoice.com/templates";


    public void openTemplate(String status) throws AWTException {

        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.invisibilityOf(overlay));
        wait.until(ExpectedConditions.elementToBeClickable(testingBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesBtn));
        js.executeScript("arguments[0].click();", templatesBtn);
//        wait.until(ExpectedConditions.invisibilityOf(overlay));
        js.executeScript("arguments[0].click();", filterMenu);
//        wait.until(ExpectedConditions.elementToBeClickable(filterMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(clearFilterBtn)).click();
        selectStatus(status);
        pressEscape();
        js.executeScript("arguments[0].scrollIntoView(true);", firstResult);
        getTemplateDetails(firstResult);
        wait.until(ExpectedConditions.invisibilityOf(overlay));
        wait.until(ExpectedConditions.elementToBeClickable(firstResult)).click();
        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn));
        getTemplateOverviewDetails();
//        driver.navigate().back();
    }

    public boolean checkDisplayedBtns(String status) {
        boolean correctBtnsDisplayed = false;
        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn));
        switch (status) {
            case "ACTIVE" -> {
                if (goToRequestDetailsBtn.isDisplayed() && copyBtn.isDisplayed()
                        && archiveBtn.isDisplayed() && promoteBtn.isDisplayed() && displayedBtns.size() == 4) {
                    correctBtnsDisplayed = true;
                }
            }
            case "IN PROGRESS" -> {
                if (goToRequestDetailsBtn.isDisplayed() && buildBtn.isDisplayed()) {
                    correctBtnsDisplayed = true;
                }
            }
            case "TESTING", "COMPLETED TESTING" -> {
                if (goToRequestDetailsBtn.isDisplayed() && buildBtn.isDisplayed()
                        && goToTestingBtn.isDisplayed() && displayedBtns.size() == 3) {
                    correctBtnsDisplayed = true;
                }
            }
            case "ARCHIVED" -> {
                if (goToRequestDetailsBtn.isDisplayed()
                        && copyBtn.isDisplayed() && displayedBtns.size() == 2) {
                    correctBtnsDisplayed = true;
                }
            }
        }
        return correctBtnsDisplayed;
    }

    void selectStatus(String status) {

        switch (status) {
            case "ACTIVE" -> {
                wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
                inProgressOption.click();
                testingOption.click();
                completedOption.click();
            }
            case "IN PROGRESS" -> {
                wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
                activeOption.click();
                testingOption.click();
                completedOption.click();
            }
            case "TESTING" -> {
                wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
                inProgressOption.click();
                activeOption.click();
                completedOption.click();
            }
            case "COMPLETED TESTING" -> {
                wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
                inProgressOption.click();
                activeOption.click();
                testingOption.click();
            }
            case "ARCHIVED" -> {
                wait.until(ExpectedConditions.elementToBeClickable(showArchivedBtn)).click();
//                showArchivedBtn.click();
            }
        }
    }

    void getTemplateOverviewDetails() {
        templateOverviewValues = new ArrayList<>();
        templateOverviewValues.add(name.getAttribute("value"));
        templateOverviewValues.add(status.getAttribute("value"));
//        templateOverviewValues.add(user.getAttribute("value"));
        templateOverviewValues.add(dateCreated.getAttribute("value"));
        templateOverviewValues.add(category.getAttribute("value"));
        templateOverviewValues.add(channelType.getAttribute("value"));
        templateOverviewValues.add(version.getAttribute("value"));
    }

    void getTemplateDetails(WebElement template) {
        wait.until(ExpectedConditions.elementToBeClickable(template));
        initialValues = new ArrayList<>();
        initialValues.add(tableTemplateName.getText());
        initialValues.add(tableTemplateStatus.getText());
//        initialValues.add(tableTemplateUser.getText());
        initialValues.add(tableTemplateDate.getText());
        initialValues.add(tableTemplateCategory.getText());
        initialValues.add(tableTemplateChannelType.getText());
        initialValues.add(tableTemplateVersion.getText());

    }

    public void filterTemplatesByStatus(String status) {
        wait.until(ExpectedConditions.elementToBeClickable(filterMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        switch (status) {
            case "IN PROGRESS" -> inProgressOption.click();
            case "ACTIVE" -> activeOption.click();
            case "TESTING" -> testingOption.click();
            case "COMPLETED TESTING" -> completedOption.click();
        }
    }

    public List<String> beforeCopy = new ArrayList<>();
    public List<String> afterCopy = new ArrayList<>();

    public String copyStatus;

    public String urlBeforeCopy;
    public void copyTemplate(String status) throws AWTException {
        openTemplate(status);
        getTemplateOverviewDetails();
        urlBeforeCopy = driver.getCurrentUrl();
        beforeCopy = templateOverviewValues;
        wait.until(ExpectedConditions.elementToBeClickable(copyBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(approversDrpdwn));
        approversDrpdwn.click();
//        js.executeScript("arguments[0].click();", approversDrpdwn);
        wait.until(ExpectedConditions.elementToBeClickable(firstApprover)).click();
        pressEscape();
        String creator = wait.until(ExpectedConditions.elementToBeClickable(templateCreatorDrpdwn)).getText();
        wait.until(ExpectedConditions.elementToBeClickable(createBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn));
        js.executeScript("arguments[0].click();", goToRequestDetailsBtn);
//        wait.until(ExpectedConditions.elementToBeClickable(goToTemplateDetailsBtn));
//        js.executeScript("arguments[0].click();", goToTemplateDetailsBtn);
//        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(buildBtn));
        getTemplateOverviewDetails();
        afterCopy = templateOverviewValues;
        copyStatus = afterCopy.get(1);
    }

    public boolean goToRequestDetails(String status) throws AWTException {
        openTemplate(status);
        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn)).click();
        wait.until(ExpectedConditions.urlContains("/requests"));
        boolean urlIsValid = driver.getCurrentUrl().contains("/requests");
        wait.until(ExpectedConditions.elementToBeClickable(goToTemplateDetailsBtn)).click();
        return urlIsValid;
    }

    public boolean sendForTesting(String status) throws AWTException {
        openTemplate(status);
        wait.until(ExpectedConditions.elementToBeClickable(buildBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(templateBuilder));
////        wait.until(ExpectedConditions.elementToBeClickable(builderInputField));
//        Actions actions = new Actions(driver);
//        builderInputField.click();
//        builderInputField.click();
////        actions.doubleClick(builderInputField).sendKeys("test").build().perform();
//        builderInputField.sendKeys("test");
        wait.until(ExpectedConditions.elementToBeClickable(goBackBtn)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(goBackBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(sendForTestingBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(testerGroupsDrpdwn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(firstOption)).click();
        pressEscape();
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        wait.until(ExpectedConditions.urlContains("/testing"));
        return driver.getCurrentUrl().contains("/testing");
    }

    public boolean editTemplate(String status) throws AWTException {
        openTemplate(status);
        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(requestNameField));
        js.executeScript("arguments[0].click();", requestNameField);
        requestNameField.clear();
        requestNameField.sendKeys("NewName");
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        String templateName = name.getAttribute("value");
        return templateName.equals("NewName");
    }

    public boolean completedMessageIsDisplayed = false;
    public boolean statusIsChanged = false;
    public String actualMessage;
    public String expectedMessage;

    public void goToTesting(String templateStatus) throws AWTException {
        openTemplate(templateStatus);
        wait.until(ExpectedConditions.elementToBeClickable(goToTemplateTestingBtn)).click();
        wait.until(ExpectedConditions.urlContains("/testing"));
        wait.until(ExpectedConditions.textToBePresentInElement(h2, "has been completed"));
        actualMessage = h2.getText();
        expectedMessage = "The testing for this Template has been completed.";
    }

    public void completeTemplateTesting(String templateStatus) throws AWTException {
        openTemplate(templateStatus);
        wait.until(ExpectedConditions.elementToBeClickable(goToTemplateTestingBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(h2));
        if (!h2.getText().contains("dedicated")) {
            driver.navigate().refresh();
        }
        wait.until(ExpectedConditions.elementToBeClickable(completeTestingBtn)).click();
//        wait.until(ExpectedConditions.invisibilityOf(overlay));
//        wait.until(ExpectedConditions.elementToBeClickable(completeTestingBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
        wait.until(ExpectedConditions.textToBePresentInElement(h2, "has been completed"));
        String completedMessage = driver.findElement(By.xpath("(//h2)[1]")).getText();
        wait.until(ExpectedConditions.elementToBeClickable(goToTemplateDetailsBtn)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(status));
        String newStatus = status.getAttribute("value");
        System.out.println(completedMessage);
        if (completedMessage.equals("The testing for this Template has been completed.")) {
            completedMessageIsDisplayed = true;
        }
        if (newStatus.contains("COMPLETED TESTING")) {
            statusIsChanged = true;
        }
    }

    public void archiveTemplate(String templateStatus) throws AWTException {
        openTemplate(templateStatus);
        wait.until(ExpectedConditions.elementToBeClickable(archiveBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
    }

    String approverss;

    public void sendForApproval(String templatesStatus) throws AWTException {
        openTemplate(templatesStatus);
        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(sendForApprovalBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
    }

    public boolean rejectButtonVisible = true;

    public String reject() throws AWTException {
        openTemplate("COMPLETED TESTING");
        wait.until(ExpectedConditions.elementToBeClickable(goToRequestDetailsBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(rejectBtn)).click();
        wait.until(ExpectedConditions.invisibilityOf(rejectBtn));
        if (!rejectBtn.isDisplayed()) {
            rejectButtonVisible = false;
        }
        return status.getAttribute("value");


    }


}
