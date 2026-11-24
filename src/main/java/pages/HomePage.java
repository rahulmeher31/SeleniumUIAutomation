package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    public static final String HOME_PAGE_URL = "";

    private final By brandLogo = By.cssSelector("a[title='Practice Software Testing - Toolshop'], a.navbar-brand");
    private final By searchInput = By.cssSelector("[data-test='search-query']");
    private final By searchSubmitBtn = By.cssSelector("[data-test='search-submit']");
    private final By searchResetBtn = By.cssSelector("[data-test='search-reset']");
    private final By productCards = By.cssSelector("a.card");
    private final By productTitles = By.cssSelector("[data-test='product-name']");
    private final By navHomeLink = By.cssSelector("[data-test='nav-home']");
    private final By navCategoriesDropdown = By.cssSelector("[data-test='nav-categories']");
    private final By navContactLink = By.cssSelector("[data-test='nav-contact']");
    private final By navSignInLink = By.cssSelector("[data-test='nav-sign-in']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isBrandLogoDisplayed() {
        return isDisplayed(brandLogo);
    }

    public void searchProduct(String productName) {
        sendKeys(searchInput, productName);
        click(searchSubmitBtn);
    }

    public void resetSearch() {
        click(searchResetBtn);
    }

    public List<String> getProductNames() {
        List<WebElement> elements = waitForElements(productTitles);
        List<String> names = new ArrayList<>();
        for (WebElement element : elements) {
            names.add(element.getText());
        }
        return names;
    }

    public int getProductCount() {
        return waitForElements(productCards).size();
    }

    public void navigateToContact() {
        click(navContactLink);
    }

    public void navigateToSignIn() {
        click(navSignInLink);
    }
}
