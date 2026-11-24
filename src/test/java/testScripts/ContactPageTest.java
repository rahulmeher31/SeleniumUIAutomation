package testScripts;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactPage;

public class ContactPageTest extends BaseTest {

    @Test(description = "Verify Contact Form Submission", priority = 1, groups = "smoke")
    public void testContactFormSubmission() {
        ContactPage contactPage = new ContactPage(getDriver());

        navigateToTestUrl(ContactPage.CONTACT_PAGE_URL);
        contactPage.fillContactForm(
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "Customer service",
                "This is an automated test message for customer service inquiry."
        );
        contactPage.submitForm();

        Assert.assertTrue(contactPage.isSuccessAlertDisplayed(), "Success alert should be displayed after form submission");
        Assert.assertTrue(contactPage.getSuccessAlertText().toLowerCase().contains("thanks for your message"),
                "Success alert message should confirm receipt");
    }
}
