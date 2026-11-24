package testScripts;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {

    @Test(description = "Verify Invalid Login Shows Error Message", priority = 1, groups = "smoke")
    public void testInvalidLoginShowsError() {
        LoginPage loginPage = new LoginPage(getDriver());

        navigateToTestUrl(LoginPage.LOGIN_PAGE_URL);
        loginPage.login("invalid.user@example.com", "WrongPassword123!");

        Assert.assertTrue(loginPage.isErrorAlertDisplayed(), "Error alert should be displayed for invalid credentials");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid email or password"), "Error message should notify invalid credentials");
    }
}
