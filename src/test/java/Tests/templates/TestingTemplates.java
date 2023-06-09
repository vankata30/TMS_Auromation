package Tests.templates;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.templates.TemplatesPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class TestingTemplates extends BaseTest {
    TemplatesPage templatesPage;

    @Test(priority = 1)
    public void verifyTestingTemplate() throws AWTException {
        templatesPage = new TemplatesPage(driver);
        templatesPage.openTemplate("TESTING");
        //verifying the correct data is displayed in Template Overview
        Assert.assertTrue(templatesPage.compareValues());
        //verifying the correct buttons for an "TESTING" template are displayed
        Assert.assertTrue(templatesPage.checkDisplayedBtns("TESTING"));
    }

    @Test(priority = 2)
    public void completeTemplateTesting() throws AWTException{
        templatesPage = new TemplatesPage(driver);
        templatesPage.completeTemplateTesting("TESTING");
        Assert.assertTrue(templatesPage.completedMessageIsDisplayed);
        Assert.assertTrue(templatesPage.statusIsChanged);
    }
}
