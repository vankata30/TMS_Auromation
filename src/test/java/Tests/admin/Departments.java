package Tests.admin;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.admin.DepartmentsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Departments extends BaseTest {

    DepartmentsPage departmentsPage;

    @Test
    public void createNewDepartment(){
        departmentsPage = new DepartmentsPage(driver);
        departmentsPage.createNewDepartment().goToLastPage();
        Assert.assertEquals(departmentsPage.getNewDepartmentName(), departmentsPage.name);
        Assert.assertEquals(departmentsPage.getNewDepartmentStatus(), "true");
    }

    @Test
    public void editDepartment(){
        departmentsPage.editDepartment();
        Assert.assertEquals(departmentsPage.getNewDepartmentNewName(), departmentsPage.newName);
        Assert.assertEquals(departmentsPage.getNewDepartmentCode(), departmentsPage.code);
        Assert.assertEquals(departmentsPage.getNewDepartmentStatus(), departmentsPage.checkboxStatus);
    }
}
