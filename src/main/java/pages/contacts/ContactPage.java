package pages.contacts;

import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.leads.ManageLeadPage;

public class ContactPage extends WebPageUtils {

    @FindBy(css = ".AppHeader_subComponents_secondaryActions_96a > button:first-child")
    protected WebElement addLeadButtonElement;

    @FindBy(css = "[class*=\"ResultsLimit_count\"]")
    protected WebElement contactCountElement;

    @FindBy(css = "[class*=\"AppHeader_actions\"] > div:first-child > div > button")
    protected WebElement moreOptionButtonElement;

    @FindBy(css = "[class*=\"DropdownMenu_l\"] > li:last-child")
    protected WebElement bulkEditButtonElement;

    @FindBy(id = "SelectToggleButton_BulkEdit__ActionType")
    protected WebElement bulkEditMenuOptionElement;

    @FindBy(xpath = "//*[text() = \"Delete the contacts\"]")
    protected WebElement deleteContactsMenuOptionElement;



    @FindBy(css = "div .Btn.Btn--primary")
    protected WebElement bulkEditNextButtonElement;

    @FindBy(css = "div > button[class=\"Btn\"]")
    protected WebElement okButtonElement;

    @FindBy(xpath = "//span[text() = 'Task completed: ']")
    protected WebElement taskCompletedElement;


    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public ManageLeadPage clickAddLeadButton() {
        waitUntilElementVisibility(contactCountElement, 10);
        waitAndClick(addLeadButtonElement);
        return PageFactory.initElements(driver, ManageLeadPage.class);
    }

    public void deleteAllContacts() {
        waitUntilElementVisibility(contactCountElement, 5);
        int totalContacts = getContactCount();
        if (totalContacts != 0) {
            waitAndClick(moreOptionButtonElement);
            waitAndClick(bulkEditButtonElement);
            waitAndClick(bulkEditMenuOptionElement);
            moveToElementAndClick(deleteContactsMenuOptionElement);
            waitAndClick(bulkEditNextButtonElement);
            waitAndClick(bulkEditNextButtonElement);
        } else {
            System.out.println("There is no lead available to delete");
        }
    }

    public int getContactCount() {
        waitUntilElementVisibility(contactCountElement, 7);
        return Integer.parseInt(contactCountElement.getText());
    }

    public boolean isOkButtonAvailable() {
        try {
            waitUntilElementVisibility(okButtonElement, 5);
        } catch (Exception e) {
            return false;
        }
        clickOnOkButton();
        return true;
    }

    public boolean isTaskCompleted() {
        waitUntilElementVisibility(taskCompletedElement, 6);
        return taskCompletedElement.isDisplayed();
    }

    public void clickOnOkButton() {
        waitAndClick(okButtonElement);
    }



}
