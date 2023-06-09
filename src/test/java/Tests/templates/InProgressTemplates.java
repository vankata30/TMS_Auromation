package Tests.templates;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.templates.TemplatesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class InProgressTemplates extends BaseTest {
    TemplatesPage templatesPage;

    @Test (priority = 1)
    public void verifyInProgressTemplate() throws AWTException {
        templatesPage = new TemplatesPage(driver);
        templatesPage.openTemplate("IN PROGRESS");
        //verifying the correct data is displayed in Template Overview
        Assert.assertTrue(templatesPage.compareValues());
        //verifying the correct buttons for an "IN PROGRESS" template are displayed
        Assert.assertTrue(templatesPage.checkDisplayedBtns("IN PROGRESS"));
    }
    @Test(priority = 2)
    public void editTemplate() throws AWTException{
        templatesPage = new TemplatesPage(driver);
        Assert.assertTrue(templatesPage.editTemplate("IN PROGRESS"));
    }
    @Test(priority = 3)
    public void sendForTesting() throws AWTException{
        templatesPage = new TemplatesPage(driver);
        templatesPage.openTemplate("IN PROGRESS");
        templatesPage.sendForTesting("IN PROGRESS");
        Assert.assertTrue(templatesPage.sendForTesting("IN PROGRESS"), "true");
    }


}
