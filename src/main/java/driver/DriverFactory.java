package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.ConfigReader;

public class DriverFactory {

    // Thread-local storage for the WebDriver instance
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // Private constructor to prevent direct instantiation
    private DriverFactory() {}

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    private static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static WebDriver initDriver(String browser) {
        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.getPropertyValue("browser");
        }

        boolean isHeadless = Boolean.parseBoolean(ConfigReader.getPropertyValue("headless"));
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        if (Boolean.parseBoolean(ConfigReader.getPropertyValue("maximize"))) {
            driver.manage().window().maximize();
        }

        driver.manage().deleteAllCookies();
        setDriver(driver);
        return getDriver();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove(); // Crucial to prevent memory leaks across threads
        }
    }
}