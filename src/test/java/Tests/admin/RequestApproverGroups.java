package Tests.admin;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.admin.ApproverGroupsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class RequestApproverGroups extends BaseTest {

    ApproverGroupsPage approverGroupsPage;

    @Test
    public void createNewApproverGroup() throws AWTException {
        approverGroupsPage = new ApproverGroupsPage(driver);
        approverGroupsPage.createNewApproverGroup();
        Assert.assertEquals(getUserEmail(approverGroupsPage.name),approverGroupsPage.approver1);
        Assert.assertEquals(getStatus(approverGroupsPage.name), "true");
    }

    @Test
    public void editApproverGroup() throws AWTException {
        approverGroupsPage.editApproverGroup();
        Assert.assertTrue(getUserEmail(approverGroupsPage.newName).contains(approverGroupsPage.approver2));
        Assert.assertEquals(getStatus(approverGroupsPage.newName), "false");
    }
}
