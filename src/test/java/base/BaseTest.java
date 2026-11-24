package testScripts;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest{

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeDriver driver = new ChromeDriver();
        }
    }
}
