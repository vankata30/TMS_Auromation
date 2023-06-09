package Tests.admin;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.admin.ChannelTypesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.*;

public class ChannelTypes extends BaseTest {

    ChannelTypesPage channelTypesPage;

    @Test
    public void editChannelType() throws AWTException {
        channelTypesPage = new ChannelTypesPage(driver);
        channelTypesPage.editChannelType();
        Assert.assertEquals(channelTypesPage.actualRequestTypes.getText(), channelTypesPage.expectedRequestTypes);
        Assert.assertEquals(channelTypesPage.actualActiveStatus.getText(), channelTypesPage.expectedStatus);
    }

}
