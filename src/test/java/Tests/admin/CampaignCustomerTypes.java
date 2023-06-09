package Tests.admin;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.admin.CustomerTypesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CampaignCustomerTypes extends BaseTest {
    CustomerTypesPage customerTypesPage;

    @Test
    public void createNewCustomerType(){
        customerTypesPage = new CustomerTypesPage(driver);
        customerTypesPage.createNewCustomerType();
        Assert.assertEquals(getStatus(customerTypesPage.name), "true");
    }

    @Test
    public void editCustomerType(){
        customerTypesPage.editCustomerType();
        Assert.assertEquals(getStatus(customerTypesPage.newName), "false");
    }
}
