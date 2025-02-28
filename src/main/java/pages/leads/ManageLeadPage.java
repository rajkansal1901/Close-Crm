package pages.leads;

import generalUtility.utilities.GeneralUtils;
import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.imports.ImportPage;

import java.util.List;

public class ManageLeadPage extends WebPageUtils {

    @FindBy(css = ".NewLeadModalContainer_inputs_3ba > div:first-child  input")
    protected WebElement inputCompanyNameElement;

    @FindBy(css = ".NewLeadModalContainer_inputs_3ba > div:last-child  input")
    protected WebElement inputContactNameElement;

    @FindBy(css = ".subcomponents_footerComponents_actions_3ca > button:last-child")
    protected WebElement createLeadButtonElement;

    @FindBy(css = "h3 > .Tooltip_TruncateText_truncated_801")
    protected WebElement leadTitleElement;

    @FindBy(css = "[aria-label= 'Add Contact']")
    protected WebElement addContactButtonElement;

    @FindBy(css = "input[aria-label=\"Contact Info\"]")
    protected WebElement inputContactInfoElement;

    @FindBy(id = "SelectToggleButton_contact-info-type-select")
    protected WebElement contactInfoMenuElement;

    @FindBy(xpath = "//div[text() = \"Mobile\"]")
    protected WebElement contactTypeAsMobileElement;

    @FindBy(xpath = "//div[text() = \"Direct\"]")
    protected WebElement contactTypeAsDirectElement;

    @FindBy(xpath = "//div[text() = \"Office\"]")
    protected WebElement contactTypeAsOfficeElement;

    @FindBy(id = "LeadContactForm__Submit")
    protected WebElement saveContactInformationElement;

    @FindBy(css = "span[class*=\"Badge_small_87f Badge_isCircle_2fd Badge_default_708 Badge_outlined_bbd\"]")
    protected WebElement contactDetailsTitleElement;

    @FindBy(css = "[aria-labelledby=\"modalTitle\"] a[href=\"/import/\"]")
    protected WebElement importLeadDataElement;

    @FindBy(css = "[aria-label=\"Add Task\"]")
    protected WebElement addTaskElement;

    @FindBy(css = "[aria-label=\"Add Opportunity\"]")
    protected WebElement addOpportunityElement;

    @FindBy(css = "[aria-label=\"Date\"]")
    protected WebElement inputDateElement;

    @FindBy(css = "button[type=\"submit\"]")
    protected WebElement submitButtonElement;

    @FindBy(css = "[tabindex=\"-1\"] > li")
    protected List<WebElement> timeInputElements;

    @FindBy(css = "[class*=\"d Badge_default_708 Badge_outlined_bbd\"")
    protected WebElement taskCountElement;

    @FindBy(css = "[aria-label=\"Opportunity Value\"]")
    protected WebElement opportunityValueElement;

    @FindBy(css = "[id*=\"Opportunity Estimated Close\"]")
    protected WebElement opportunityEstimatedCloseDateElement;

    @FindBy(css = "div[id*=\"Opportunity Contact\"]")
    protected WebElement selectOpportunityContactElement;

    @FindBy(css = "[id *= \"item-5\"]")
    protected WebElement deleteLeadButtonElement;

    @FindBy(css = ".Btn.Btn--danger")
    protected WebElement confirmDeleteButtonElement;

    @FindBy(css = "button[id *= \"downshift-\"][aria-label=\"Manage Lead\"]")
    protected WebElement manageLeadElement;

    @FindBy(css = "[aria-label=\"Mark as complete\"]")
    protected WebElement markAsDoneButtonElement;

    public ManageLeadPage(WebDriver driver) {
        super(driver);
    }

    public void addNewLead(String companyName) {
        String name = GeneralUtils.getUniqueName("Skyler");
        waitAndClickElementAndSendKeys(inputCompanyNameElement, companyName);
        waitAndClickElementAndSendKeys(inputContactNameElement, name);
        waitAndClick(createLeadButtonElement);
    }

    public void addNewLead(String companyName, String contactName) {
        waitAndClickElementAndSendKeys(inputCompanyNameElement, companyName);
        waitAndClickElementAndSendKeys(inputContactNameElement, contactName);
        waitAndClick(createLeadButtonElement);
    }

    public boolean getLeadTitle(String title) {
        waitUntilElementVisibility(leadTitleElement, 5);
        return leadTitleElement.getText().equals(title);
    }

    public void addContactInfo(String personalContactNumber, String email, String officeContactNumber) {
        addCustomerData(personalContactNumber, contactTypeAsMobileElement);
        addCustomerData(email, contactTypeAsOfficeElement);
        addCustomerData(officeContactNumber, contactTypeAsDirectElement);
    }

    public int getTotalContactInfoCount() {
        waitUntilElementVisibility(contactDetailsTitleElement, 5);
        return Integer.parseInt(contactDetailsTitleElement.getText());
    }

    public void addCustomerData(String contactInfo, WebElement contactTypeElement) {
        waitAndClick(addContactButtonElement);
        waitAndClickElementAndSendKeys(inputContactInfoElement, contactInfo);
        waitUntilElementVisibility(contactInfoMenuElement, 10);
        waitAndClick(contactInfoMenuElement);
        moveToElementAndClick(contactTypeElement);
        waitAndClick(saveContactInformationElement);
        moveToElementAndClick(contactDetailsTitleElement);
    }

    public ImportPage clickImportDataOption() {
        waitAndClick(importLeadDataElement);
        return PageFactory.initElements(driver, ImportPage.class);
    }

    public void deleteLead(String companyName) {
        waitAndClick(manageLeadElement);
        waitAndClick(deleteLeadButtonElement);
        waitAndClick(confirmDeleteButtonElement);
    }

    public void addTask() {
        waitAndClick(addTaskElement);
        waitAndClick(inputDateElement);
        inputDateElement.clear();
        moveToElementAndClick(inputDateElement);
        doSendKeys(inputDateElement, GeneralUtils.getCurrentDate());
        pressKey(Keys.TAB);
        moveToElementAndClick(timeInputElements.get(6));
        pressKey(Keys.TAB);
        waitAndClick(submitButtonElement);
    }

    public int getTaskCount() {
        waitUntilElementVisibility(taskCountElement, 5);
        return Integer.parseInt(taskCountElement.getText());
    }

    public void addNewOpportunity() {
        waitAndClick(addOpportunityElement);
        waitAndClickElementAndSendKeys(opportunityValueElement, "2500");
        waitAndClick(opportunityEstimatedCloseDateElement);
        opportunityEstimatedCloseDateElement.clear();
        moveToElementAndClick(opportunityEstimatedCloseDateElement);
        doSendKeys(opportunityEstimatedCloseDateElement, GeneralUtils.getCurrentDate());
        moveToElementAndClick(selectOpportunityContactElement);
        pressKey(Keys.TAB);
        moveToElementAndClick(submitButtonElement);
    }

    public void clickOnDoneButton() {
        waitAndClick(markAsDoneButtonElement);
    }

}


