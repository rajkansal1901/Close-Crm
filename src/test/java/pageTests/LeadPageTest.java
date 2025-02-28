package pageTests;

import base.BaseTest;
import generalUtility.utilities.GeneralUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.imports.ImportPage;
import pages.leads.LeadPage;
import pages.leads.ManageLeadPage;

public class LeadPageTest extends BaseTest {

    protected LeadPage leadPage;
    protected ManageLeadPage manageLeadPage;
    protected ImportPage importPage;


    @AfterMethod
    public void backToHomePage() {
        homePage = homePage.goToInboxPage();
    }

    @BeforeMethod
    public void goToLeadsPage() {
        leadPage = homePage.goToLeadsPage();
    }

    @Test(priority = 1, dataProvider = "lead")
    public void createLeadTest(String username , String password) {
        manageLeadPage = leadPage.clickAddLeadButton();
//        String companyName = GeneralUtils.getUniqueName("Aryan Ltd ");
        manageLeadPage.addNewLead(username, password);
        Assert.assertTrue(manageLeadPage.getLeadTitle(username), "No lead Created");
    }


    @Test(priority = 1)
    public void createLeadTest() {
        manageLeadPage = leadPage.clickAddLeadButton();
        String companyName = GeneralUtils.getUniqueName("Aryan Ltd ");
        manageLeadPage.addNewLead(companyName);
        Assert.assertTrue(manageLeadPage.getLeadTitle(companyName), "No lead Created");
    }

    @Test(priority = 2)
    public void deleteLeadTest() {
        manageLeadPage = leadPage.clickAddLeadButton();
        String companyName = GeneralUtils.getUniqueName("Abc");
        manageLeadPage.addNewLead(companyName);
        manageLeadPage.deleteLead(companyName);
        leadPage = homePage.goToLeadsPage();
        Assert.assertFalse(leadPage.isLeadAvailable(companyName));
    }

    @Test(priority = 3)
    public void importLeadTest() {
        String filePath = "C:\\batch\\project\\report-2025-01-19T00_49_37.288Z";
        manageLeadPage = leadPage.clickAddLeadButton();
        importPage = manageLeadPage.clickImportDataOption();
        importPage.importData(filePath);
        Assert.assertTrue(importPage.isImportSuccessFul(), "Import Failed");
    }

    @Test(priority = 4)
    public void mergeDuplicateLeadsTest() {
        String companyName = GeneralUtils.getUniqueName("Raj");
        manageLeadPage = leadPage.clickAddLeadButton();
        manageLeadPage.addNewLead(companyName);
        leadPage = homePage.goToLeadsPage();
        manageLeadPage = leadPage.clickAddLeadButton();
        manageLeadPage.addNewLead(companyName);
        leadPage.mergeDuplicateLeads();
        Assert.assertTrue(leadPage.isLeadMerged());
    }

    @Test(priority = 5)
    public void deleteAllLeadTest() {
        leadPage.deleteAllLeads();
        Assert.assertTrue(leadPage.isDeletionConfirmed());
    }
}
