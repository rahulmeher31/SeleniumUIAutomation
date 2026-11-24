package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public static final String LOGIN_PAGE_URL = "auth/login";

    private final By emailInput = By.cssSelector("[data-test='email']");
    private final By passwordInput = By.cssSelector("[data-test='password']");
    private final By loginSubmitBtn = By.cssSelector("[data-test='login-submit']");
    private final By loginErrorAlert = By.cssSelector("[data-test='login-error'], .alert-danger, .help-block");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        click(loginSubmitBtn);
    }

    public boolean isErrorAlertDisplayed() {
        return isDisplayed(loginErrorAlert);
    }

    public String getErrorMessage() {
        return getText(loginErrorAlert);
    }
}
