package Tests.admin;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.admin.DepartmentsPage;
import org.selenium.pom.pages.admin.MessageTriggersPage;
import org.selenium.pom.pages.admin.SenderSystemsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SenderSystems extends BaseTest {
    SenderSystemsPage senderSystemsPage;

    @Test
    public void createNewDepartment(){
        senderSystemsPage = new SenderSystemsPage(driver);
        senderSystemsPage.createNewSenderSystem();
        Assert.assertEquals(getStatus(senderSystemsPage.name), "true");
    }

    @Test
    public void editDepartment(){
        senderSystemsPage.editSenderSystem();
        Assert.assertEquals(getStatus(senderSystemsPage.newName), "false");
    }
}
