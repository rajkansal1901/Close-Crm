package pageTests;

import base.BaseTest;
import generalUtility.utilities.GeneralUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.contacts.ContactPage;
import pages.leads.ManageLeadPage;
import pages.opportunity.OpportunitiesPage;

public class OpportunityPageTest extends BaseTest {

    protected OpportunitiesPage opportunitiesPage;
    protected ContactPage contactPage;
    protected ManageLeadPage manageLeadPage;

    @AfterMethod
    public void goToHomePage() {
        homePage = homePage.goToInboxPage();
    }

//    @BeforeMethod
//    public void goToOpportunityPage() {
//        opportunitiesPage = homePage.goToOpportunitiesPage();
//    }

    @Test(priority = 1)
    public void dragOpportunityTest() {
        contactPage = homePage.goToContactsPage();
        String companyName = GeneralUtils.getUniqueName("Josh");
        manageLeadPage = contactPage.clickAddLeadButton();
        manageLeadPage.addNewLead(companyName);
        manageLeadPage.addNewOpportunity();
        opportunitiesPage = homePage.goToOpportunitiesPage();
        opportunitiesPage.dragOpportunityToProposal(companyName);
        Assert.assertTrue(opportunitiesPage.isOpportunityDragged(companyName));
    }

}
