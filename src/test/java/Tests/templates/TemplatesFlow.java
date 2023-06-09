package Tests.templates;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.templates.TemplatesFlowPage;
import org.selenium.pom.pages.templates.TemplatesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class TemplatesFlow extends BaseTest {
    TemplatesFlowPage templatesPage;
    TemplatesPage tp;

    @Test
    public void buildTemplate() throws AWTException {
        templatesPage = new TemplatesFlowPage(driver);
        templatesPage.buildTemplate();
    }
    @Test(priority = 1)
    public void sendForTesting() throws AWTException, InterruptedException {
        templatesPage = new TemplatesFlowPage(driver);
        Assert.assertTrue(templatesPage.sendForTesting());
    }

    @Test(priority = 2)
    public void completeTesting() throws AWTException {
        tp = new TemplatesPage(driver);
        templatesPage.completeTesting();
        Assert.assertTrue(templatesPage.completeMessageIsDisplayed);
        Assert.assertTrue(templatesPage.statusIsChanged);
    }

    @Test(priority = 3)
    public void reject() throws AWTException {
        templatesPage = new TemplatesFlowPage(driver);
        //Verifies the status of the request has changed to "REJECTED"
        Assert.assertEquals(templatesPage.reject(), "REJECTED");
        Assert.assertFalse(templatesPage.rejectButtonIsVisible);
    }

    @Test(priority = 4)
    public void approve() throws AWTException {
        templatesPage = new TemplatesFlowPage(driver);
        //Verifies the status of the request has changed to "REJECTED"
        Assert.assertEquals(templatesPage.approve(), "APPROVED");
        Assert.assertFalse(templatesPage.approveButtonIsVisible);
        Assert.assertTrue(templatesPage.publishButtonIsVisible);
    }

    @Test(priority = 5)
    public void publish() throws InterruptedException {
        templatesPage = new TemplatesFlowPage(driver);
        Assert.assertEquals(templatesPage.publish(), "COMPLETED");
        Assert.assertFalse(templatesPage.publishButtonIsVisible);
        Assert.assertTrue(tp.checkDisplayedBtns("ACTIVE"));

    }

    @Test(priority = 6)
    public void copyTemplate() throws AWTException {
        templatesPage = new TemplatesFlowPage(driver);
        tp = new TemplatesPage(driver);
        templatesPage.copyTemplate();
        Assert.assertTrue(tp.compareValues(tp.beforeCopy,tp.afterCopy));
        Assert.assertEquals(templatesPage.afterCopyStatus, "IN PROGRESS");
        Assert.assertTrue(tp.checkDisplayedBtns("IN PROGRESS"));
    }

    @Test(priority = 7)
    public void archive() {
        templatesPage = new TemplatesFlowPage(driver);
        Assert.assertEquals(templatesPage.archive(), "ARCHIVED");
        Assert.assertFalse(templatesPage.archiveButtonIsVisible);
    }

}
