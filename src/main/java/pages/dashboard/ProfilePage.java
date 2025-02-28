package pages.dashboard;

import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends WebPageUtils {

    @FindBy(css = "[class*=\"ProfilePopover_ProfilePopover_ProfilePopove\"] > a:first-child")
    protected WebElement accountMenuOptionElement;

    @FindBy(css = "[class*=\"ProfilePopover_ProfilePopover_ProfilePopove\"] > a:nth-child(2)")
    protected WebElement importDataMenuOptionElement;

    @FindBy(css = "[class*=\"ProfilePopover_ProfilePopover_ProfilePopove\"] > a:nth-child(3)")
    protected WebElement invitePeopleMenuOptionElement;

    @FindBy(css = "[class*=\"ProfilePopover_ProfilePopover_ProfilePopove\"] > a:nth-child(4)")
    protected WebElement productUpdatesMenuOptionElement;

    @FindBy(css = " a[href=\"/logout/\"]")
    protected WebElement logOutMenuOptionElement;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void goTo(String profileMenuOption) {
        switch (profileMenuOption.trim().toLowerCase()) {
            case ("account"):
                waitAndClick(accountMenuOptionElement);
                break;

            case ("import data"):
                waitAndClick(importDataMenuOptionElement);
                break;

            case ("invite people"):
                waitAndClick(invitePeopleMenuOptionElement);
                break;

            case ("product updates"):
                waitAndClick(productUpdatesMenuOptionElement);
                break;

            case ("log out"):
                waitAndClick(logOutMenuOptionElement);
                break;

            default:
                System.out.println("Enter correct option");
        }
    }

    public void logOut() {
        goTo("log out");
    }
}
