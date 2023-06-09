package Tests.templates;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.templates.TemplatesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class CompletedTestingTemplate extends BaseTest {
    TemplatesPage templatesPage;

    @Test
    public void verifyCompletedTestingTemplate() throws AWTException {
        templatesPage = new TemplatesPage(driver);
        templatesPage.openTemplate("COMPLETED TESTING");
        //verifying the correct data is displayed in Template Overview
        Assert.assertTrue(templatesPage.compareValues());
        //verifying the correct buttons for an "COMPLETED TESTING" template are displayed
        Assert.assertTrue(templatesPage.checkDisplayedBtns("COMPLETED TESTING"));
    }

    @Test
    public void goToTemplateTesting() throws AWTException {
        templatesPage = new TemplatesPage(driver);
        templatesPage.goToTesting("COMPLETED TESTING");
        Assert.assertEquals(templatesPage.expectedMessage, templatesPage.actualMessage);
    }

    @Test
    public void sendForApproval() throws AWTException {
        templatesPage = new TemplatesPage(driver);
        templatesPage.sendForApproval("COMPLETED TESTING");
    }
//    @Test
//    public void rejectApproval()throws AWTException {
//        templatesPage=new TemplatesPage(driver);
//        //Verifies the status of the request has changed to "REJECTED"
//        Assert.assertEquals(templatesPage.reject(),"REJECTED");
//        Assert.assertFalse(templatesPage.rejectButtonVisible);
//    }
}




