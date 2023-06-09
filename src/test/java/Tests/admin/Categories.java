package Tests.admin;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.admin.CategoriesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Categories extends BaseTest {

    CategoriesPage categoriesPage;

    @Test
    public void createNewCategory() throws Throwable{
             loginPage.login();

        categoriesPage = new CategoriesPage(driver);
        categoriesPage.createNewCategory().goToLastPage();
        Assert.assertEquals(
                categoriesPage.getSubcategories(categoriesPage.categoryName), categoriesPage.subcategoryName);
    }

    @Test
    public void editCategory() throws Throwable {
        categoriesPage = new CategoriesPage(driver);
        categoriesPage.editCategory();
        Assert.assertTrue(
                categoriesPage.getNewSubcategories(categoriesPage.categoryName)
                        .contains(categoriesPage.newSubcategory));
    }
}
