package pages.login;

import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.dashboard.HomePage;

public class LoginPage extends WebPageUtils {

    @FindBy(name = "email")
    protected WebElement inputEmailElement;

    @FindBy(name = "password")
    protected WebElement inputPasswordElement;

    @FindBy(css = "[type=\"submit\"]")
    protected WebElement loginButtonElement;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage performLogin() {
        waitAndClickElementAndSendKeys(inputEmailElement, getProperty("username"));
        waitAndClickElementAndSendKeys(inputPasswordElement, getProperty("password"));
        waitAndClick(loginButtonElement);
        return PageFactory.initElements(driver, HomePage.class);
    }
}
