package Tests.templates;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.templates.TemplatesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class ActiveTemplate extends BaseTest {
    TemplatesPage templatesPage;

    @Test (priority = 1)
    public void verifyActiveTemplate() throws AWTException {
        templatesPage = new TemplatesPage(driver);
        templatesPage.openTemplate("ACTIVE");
        //verifying the correct data is displayed in Template Overview
        Assert.assertTrue(templatesPage.compareValues());
        //verifying the correct buttons for an "ACTIVE" template are displayed
        Assert.assertTrue(templatesPage.checkDisplayedBtns("ACTIVE"));
    }
    @Test (priority = 3)
    public void copyActiveTemplate() throws AWTException {
        templatesPage = new TemplatesPage(driver);
        templatesPage.copyTemplate("ACTIVE");
        Assert.assertTrue(templatesPage.compareValues(templatesPage.beforeCopy,templatesPage.afterCopy));
        Assert.assertEquals(templatesPage.copyStatus, "IN PROGRESS");
        Assert.assertTrue(templatesPage.checkDisplayedBtns("IN PROGRESS"));
    }
    @Test (priority = 2)
    public void goToRequestDetails() throws AWTException {
        templatesPage = new TemplatesPage(driver);
        Assert.assertTrue(templatesPage.goToRequestDetails("ACTIVE"));
    }
    @Test
    public void archiveTemplate() throws AWTException {
        templatesPage = new TemplatesPage(driver);
        templatesPage.archiveTemplate("ACTIVE");
    }

}
