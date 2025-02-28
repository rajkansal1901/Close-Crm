package pageTests;

import base.BaseTest;
import generalUtility.utilities.GeneralUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.contacts.ContactPage;
import pages.leads.ManageLeadPage;
import pages.task.TasksPage;


public class HomePageTest extends BaseTest {
    protected ContactPage contactPage;
    protected ManageLeadPage manageLeadPage;
    protected TasksPage tasksPage;

    @AfterMethod
    public void backToHomePage() {
        homePage = homePage.goToInboxPage();
    }

    @BeforeMethod
    public void goToContactsPage() {
        contactPage = homePage.goToContactsPage();
    }

    @Test(priority = 1)
    public void markTaskAsDoneTest() {
        manageLeadPage = contactPage.clickAddLeadButton();
        String companyName = GeneralUtils.getUniqueName("Task");
        manageLeadPage.addNewLead(companyName);
        manageLeadPage.addTask();
        manageLeadPage.clickOnDoneButton();
        Assert.assertTrue(contactPage.isOkButtonAvailable());
    }
}
