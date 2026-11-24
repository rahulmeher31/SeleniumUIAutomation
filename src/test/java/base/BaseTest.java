package base;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;

public class BaseTest {

    public static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional String browser) {
        DriverFactory.initDriver(browser);
        getDriver().get(ConfigReader.getPropertyValue("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        boolean keepBrowserOpen = Boolean.parseBoolean(ConfigReader.getPropertyValue("keepAliveOnFailure"));

        if (result.getStatus() == ITestResult.FAILURE && keepBrowserOpen) {
            System.out.println("Keeping browser open for debugging. Driver will not quit.");
            return;
        }

        DriverFactory.quitDriver();
    }

    public void navigateToTestUrl(String pageUrl) {
        String url = ConfigReader.getPropertyValue("url");
        getDriver().navigate().to(url + pageUrl);
    }
}