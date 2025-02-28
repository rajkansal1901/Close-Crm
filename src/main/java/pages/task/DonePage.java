package pages.task;

import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.dashboard.HomePage;

public class DonePage extends WebPageUtils {

    @FindBy(css = ".InboxHeaderTabs_root_bc7 button:nth-child(5)")
    protected WebElement taskButtonElement;

    @FindBy(css = ".InboxHeaderTabs_root_bc7 button:nth-child(4)")
    protected WebElement smsButtonElement;

    public DonePage(WebDriver driver) {
        super(driver);
    }

    public WebPageUtils goTo(String menuOption) {

        switch (menuOption.trim().toLowerCase()) {

            case ("tasks"):
                goToPage(taskButtonElement);
                return PageFactory.initElements(driver, TasksPage.class);

            case ("sms"):
                goToPage(smsButtonElement);
                return PageFactory.initElements(driver, HomePage.class);

            default:
                System.out.println("There is no Page Available for :" + menuOption);
        }
        return null;
    }
}
