package Tests.admin;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.admin.TesterGroupsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class TesterGroups extends BaseTest {

    TesterGroupsPage testerGroupsPage;

    @Test
    public void createNewTetserGroup() throws AWTException {
        testerGroupsPage = new TesterGroupsPage(driver);
        testerGroupsPage.createNewTesterGroup();
        Assert.assertEquals(getUser(testerGroupsPage.name),testerGroupsPage.tester1);
        Assert.assertEquals(getStatus(testerGroupsPage.name), "true");
    }

    @Test
    public void editTesterGroup() throws AWTException {
        testerGroupsPage.editTesterGroup();
        Assert.assertTrue(getUser(testerGroupsPage.name).contains(testerGroupsPage.tester2));
        Assert.assertEquals(getStatus(testerGroupsPage.name), "false");
    }
}
