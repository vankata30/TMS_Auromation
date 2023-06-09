package Tests.admin;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.admin.MessageTriggersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MessageTriggers extends BaseTest {
    MessageTriggersPage triggersPage;

    @Test
    public void createNewMessageTrigger(){
        triggersPage = new MessageTriggersPage(driver);
        triggersPage.createNewMessageTrigger();
        Assert.assertEquals(getStatus(triggersPage.name), "true");
    }
    @Test
    public void editMessageTrigger(){
        triggersPage.editTriggerMessage();
        Assert.assertEquals(getStatus(triggersPage.newName), "false");
    }
}
