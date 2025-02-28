package pageTests;

import base.BaseTest;
import generalUtility.utilities.GeneralUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.contacts.ContactPage;
import pages.imports.ImportPage;
import pages.leads.ManageLeadPage;
import pages.opportunity.OpportunitiesPage;

public class ContactPageTest extends BaseTest {
    protected ContactPage contactPage;
    protected ManageLeadPage manageLeadPage;
    protected ImportPage importPage;
    protected OpportunitiesPage opportunitiesPage;

    @AfterMethod
    public void backToHomePage() {
        homePage = homePage.goToInboxPage();
    }

    @BeforeMethod
    public void goToLeadsPage() {
        contactPage = homePage.goToContactsPage();
    }

    @Test(priority = 1)
    public void createLeadTest() {
        manageLeadPage = contactPage.clickAddLeadButton();
        String companyName = GeneralUtils.getUniqueName("Northern");
        manageLeadPage.addNewLead(companyName);
        Assert.assertTrue(manageLeadPage.getLeadTitle(companyName), "No lead Created");
    }

    @Test(priority = 2)
    public void addContactInfoTest() {
        manageLeadPage = contactPage.clickAddLeadButton();
        String companyName = GeneralUtils.getUniqueName("Southern");
        manageLeadPage.addNewLead(companyName);
        manageLeadPage.addContactInfo("5145145144", "southern@gmail.com", "18001122334");
        homePage.refreshPage();
        manageLeadPage = PageFactory.initElements(driver, ManageLeadPage.class);
        Assert.assertEquals(manageLeadPage.getTotalContactInfoCount(), 4, "Adding contact information test failed");//Name, PersonalContactNumber, Email, OfficeContactNumber
    }

    @Test(priority = 3)
    public void importDataTest() {
        String filePath = "C:\\batch\\project\\report-2025-01-19T00_49_37.288Z.csv";
        manageLeadPage = contactPage.clickAddLeadButton();
        importPage = manageLeadPage.clickImportDataOption();
        importPage.importData(filePath);
        Assert.assertTrue(importPage.isImportSuccessFul(), "Import Failed");
    }

    @Test(priority = 4)
    public void deleteAllContactsTest() {
        contactPage.deleteAllContacts();
        Assert.assertTrue(contactPage.isOkButtonAvailable());
    }

    @Test(priority = 5)
    public void addNewTaskTest() {
        manageLeadPage = contactPage.clickAddLeadButton();
        String companyName = GeneralUtils.getUniqueName("ABC");
        manageLeadPage.addNewLead(companyName);
        manageLeadPage.addTask();
        Assert.assertEquals(manageLeadPage.getTaskCount(), 1);
    }

    @Test(priority = 6)
    public void addNewOpportunityTest() {
        manageLeadPage = contactPage.clickAddLeadButton();
        String companyName = GeneralUtils.getUniqueName("Canada wide");
        manageLeadPage.addNewLead(companyName);
        manageLeadPage.addNewOpportunity();
        opportunitiesPage = homePage.goToOpportunitiesPage();
        homePage.refreshPage();
        Assert.assertTrue(opportunitiesPage.isOpportunityCreated(companyName));
    }

}

