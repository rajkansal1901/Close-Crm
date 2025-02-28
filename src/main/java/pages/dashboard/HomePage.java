package pages.dashboard;

import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.SmsPage;
import pages.contacts.ContactPage;
import pages.leads.LeadPage;
import pages.opportunity.OpportunitiesPage;
import pages.report.ReportPage;
import pages.task.DonePage;
import pages.task.TasksPage;

public class HomePage extends WebPageUtils {

    @FindBy(css = ".ProfileDropdown:first-child > button")
    protected WebElement profileButtonElement;

    @FindBy(css = ".Menu_menu_501 > ul > li > div")
    protected WebElement inboxMenuOptionElement;

    @FindBy(css = ".Menu_menu_501 > ul > li:nth-child(2) > div")
    protected WebElement opportunityMenuOptionElement;

    @FindBy(css = ".Menu_menu_501 > ul > li:nth-child(3)")
    protected WebElement leadMenuOptionElement;

    @FindBy(css = ".Menu_menu_501 > ul > li:nth-child(4)")
    protected WebElement contactMenuOptionElement;

    @FindBy(css = ".Menu_menu_501 > ul > li:nth-child(5)")
    protected WebElement workFlowMenuOptionElement;

    @FindBy(css = ".Menu_menu_501 > ul > li:nth-child(7)")
    protected WebElement reportsMenuOptionElement;

    @FindBy(css = ".InboxHeaderTabs_root_bc7 button:nth-child(5)")
    protected WebElement taskButtonElement;

    @FindBy(css = "[href=\"/tasks/archive/\"]")
    protected WebElement doneButtonElement;

    @FindBy(css = ".InboxHeaderTabs_root_bc7 button:nth-child(4)")
    protected WebElement smsButtonElement;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage goToInboxPage() {
        goToPage(inboxMenuOptionElement);
        return PageFactory.initElements(driver, HomePage.class);
    }

    public OpportunitiesPage goToOpportunitiesPage() {
        goToPage(opportunityMenuOptionElement);
        return PageFactory.initElements(driver, OpportunitiesPage.class);
    }

    public LeadPage goToLeadsPage() {
        goToPage(leadMenuOptionElement);
        return PageFactory.initElements(driver, LeadPage.class);
    }

    public ContactPage goToContactsPage() {
        goToPage(contactMenuOptionElement);
        return PageFactory.initElements(driver, ContactPage.class);
    }

    public ReportPage goToReportPage() {
        goToPage(reportsMenuOptionElement);
        return PageFactory.initElements(driver, ReportPage.class);
    }

    public DonePage goToDonePage() {
        goToPage(doneButtonElement);
        return PageFactory.initElements(driver, DonePage.class);
    }
    public TasksPage goToTaskPage() {
        goToPage(taskButtonElement);
        return PageFactory.initElements(driver, TasksPage.class);
    }
    public SmsPage goToSmsPage() {
        goToPage(smsButtonElement);
        return PageFactory.initElements(driver, SmsPage.class);
    }

    public ProfilePage goToProfile() {
        waitAndClick(profileButtonElement);
        return PageFactory.initElements(driver, ProfilePage.class);
    }
}
