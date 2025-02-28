package pages.leads;

import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LeadPage extends WebPageUtils {

    @FindBy(css = ".AppHeader_subComponents_secondaryActions_96a > button:first-child")
    protected WebElement addLeadButtonElement;

    @FindBy(css = "[class*=\"ResultsLimit_count\"]")
    protected WebElement leadCountElement;

    @FindBy(css = "[class *= DataTable_body] > tr > td:first-child")
    protected List<WebElement> allLeadElement;

    @FindBy(css = "[class*=\"AppHeader_actions\"] > div:first-child > div > button")
    protected WebElement moreOptionButtonElement;

    @FindBy(xpath = "//span[text() = \"Merge\"]")
    protected WebElement mergeMessageElement;

    @FindBy(css = "[id *=item-3]")
    protected WebElement mergeLeadOptionElement;

    @FindBy(css = "button[id *= \"downshift-\"][aria-label=\"Manage Lead\"]")
    protected WebElement manageLeadButtonElement;

    @FindBy(xpath = "//*[text() = \"Merge into this lead\"]")
    protected WebElement mergeIntoLeadButtonElement;

    @FindBy(css = "[class*=\"DropdownMenu_l\"] > li:last-child")
    protected WebElement bulkEditButtonElement;

    @FindBy(css = "[data-testid=\"SelectToggleButton_BulkEdit__ActionType\"]")
    protected WebElement bulkEditMenuOptionElement;

    @FindBy(xpath = "//*[text() = \"Delete the leads\"]")
    protected WebElement deleteLeadMenuOptionElement;

    @FindBy(css = "div .Btn.Btn--primary")
    protected WebElement bulkEditNextButtonElement;

    @FindBy(css = "div > button[class=\"Btn\"]")
    protected WebElement okButtonElement;

    @FindBy(css = "[aria-label=\"Customize columns\"]")
    protected WebElement customizeColumnsElement;


    public LeadPage(WebDriver driver) {
        super(driver);
    }

    public ManageLeadPage clickAddLeadButton() {
        waitUntilElementVisibility(leadCountElement, 5);
        waitAndClick(addLeadButtonElement);
        return PageFactory.initElements(driver, ManageLeadPage.class);
    }


    public void mergeDuplicateLeads() {
        waitAndClick(manageLeadButtonElement);
        waitAndClick(mergeLeadOptionElement);
        waitUntilElementVisibility(mergeIntoLeadButtonElement, 5);
        waitAndClick(mergeIntoLeadButtonElement);
        acceptAlert();
    }


    public void deleteAllLeads() {
        waitUntilElementVisibility(leadCountElement, 5);
        int totalLeads = getLeadCount();
        if (totalLeads != 0) {
            waitAndClick(moreOptionButtonElement);
            waitAndClick(bulkEditButtonElement);
            waitAndClick(bulkEditMenuOptionElement);
            moveToElementAndClick(deleteLeadMenuOptionElement);
            waitAndClick(bulkEditNextButtonElement);
            waitAndClick(bulkEditNextButtonElement);
        } else {
            System.out.println("There is no lead available to delete");
        }
    }

    public int getLeadCount() {
        waitUntilElementVisibility(leadCountElement, 7);
        return Integer.parseInt(leadCountElement.getText());
    }

    public int getTotalLeadCount() {
        waitUntilElementVisibility(leadCountElement, 5);
        moveToElement(leadCountElement);
        return allLeadElement.size();
    }

    public boolean isDeletionConfirmed() {
        try {
            waitUntilElementVisibility(okButtonElement, 5);
        } catch (TimeoutException e) {
            return false;
        }
        okButtonElement.click();
        return true;
    }


    public boolean isLeadMerged() {
        try {
            waitUntilElementVisibility(mergeMessageElement, 10);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isLeadAvailable(String companyName) {
        waitUntilElementVisibility(customizeColumnsElement, 10);
        moveToElementAndClick(customizeColumnsElement);
        waitUntilElementVisibility(leadCountElement, 5);
        for (WebElement element : allLeadElement) {
            if (element.getText().contains(companyName)) {
                return true;
            }
        }
        return false;
    }




}
