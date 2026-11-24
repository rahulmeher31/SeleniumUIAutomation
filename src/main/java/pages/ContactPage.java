package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BasePage {

    public static final String CONTACT_PAGE_URL = "contact";

    private final By firstNameInput = By.cssSelector("[data-test='first-name']");
    private final By lastNameInput = By.cssSelector("[data-test='last-name']");
    private final By emailInput = By.cssSelector("[data-test='email']");
    private final By subjectDropdown = By.cssSelector("[data-test='subject']");
    private final By messageInput = By.cssSelector("[data-test='message']");
    private final By submitBtn = By.cssSelector("[data-test='contact-submit']");
    private final By successAlert = By.cssSelector("[data-test='contact-success-alert'], .alert-success, .alert");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void fillContactForm(String firstName, String lastName, String email, String subject, String message) {
        sendKeys(firstNameInput, firstName);
        sendKeys(lastNameInput, lastName);
        sendKeys(emailInput, email);
        selectByVisibleText(subjectDropdown, subject);
        sendKeys(messageInput, message);
    }

    public void submitForm() {
        click(submitBtn);
    }

    public boolean isSuccessAlertDisplayed() {
        return isDisplayed(successAlert);
    }

    public String getSuccessAlertText() {
        return getText(successAlert);
    }
}
