package testScripts;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.List;

public class HomePageTest extends BaseTest {

    @Test(description = "Verify Home Page Title and Brand Logo", priority = 1, groups = "smoke")
    public void testHomePageTitleAndLogo() {
        HomePage homePage = new HomePage(getDriver());

        Assert.assertTrue(homePage.isBrandLogoDisplayed(), "Brand logo should be displayed on Home Page");
        Assert.assertTrue(homePage.getPageTitle().contains("Practice Software Testing"), "Page title should contain 'Practice Software Testing'");
    }

    @Test(description = "Verify Product Catalog Listing", priority = 2, groups = "regression")
    public void testProductListingDisplayed() {
        HomePage homePage = new HomePage(getDriver());

        int count = homePage.getProductCount();
        Assert.assertTrue(count > 0, "Products should be displayed on the home page");
    }

    @Test(description = "Verify Search Product Functionality", priority = 3, groups = "regression")
    public void testProductSearch() {
        HomePage homePage = new HomePage(getDriver());

        homePage.searchProduct("Pliers");
        List<String> productNames = homePage.getProductNames();

        Assert.assertFalse(productNames.isEmpty(), "Search results should not be empty");
        for (String name : productNames) {
            Assert.assertTrue(name.toLowerCase().contains("pliers"), "Product name '" + name + "' should contain search keyword 'pliers'");
        }
    }
}
