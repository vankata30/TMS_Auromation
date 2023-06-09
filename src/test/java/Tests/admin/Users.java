package Tests.admin;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.admin.UsersPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class Users extends BaseTest {

    UsersPage usersPage;

    @Test
    public void editUser() throws AWTException {
        usersPage = new UsersPage(driver);
        usersPage.editUser();
        Assert.assertEquals(
                usersPage.getCountries(usersPage.row).getText().contains(usersPage.country),
                usersPage.firstCountrySelected);
        Assert.assertEquals(usersPage.getDepartments(usersPage.row).getText().contains(usersPage.department),
                usersPage.firstDepartmentSelected);
    }
    @Test
    public void filterUsersByEmail(){
        usersPage = new UsersPage(driver);
        usersPage.filterByEmail();
        Assert.assertEquals(usersPage.getUserEmail(usersPage.testUserEmail),usersPage.testUserEmail);
    }
}
