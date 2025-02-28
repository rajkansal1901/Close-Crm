package pages.opportunity;

import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OpportunitiesPage extends WebPageUtils {

    @FindBy(css = "[class=\"OpportunityCard_leadLink_904\"]")
    protected List<WebElement> opportunitiesTitleElement;

    @FindBy(css = ".src_wrapper > div > div > div > div")
    protected List<WebElement> allOpportunitiesElement;

    @FindBy(css = "[class*=\"Table_body_15f Table_withColumnLines\"] > div:nth-child(2) ")
    protected WebElement proposalSentColumnElement;

    @FindBy(css = "[class*=\"Table_body_15f Table_withColumnLines\"] > div:nth-child(2) > div  h4")
    protected List<WebElement> proposalSentOpportunitiesTitleElement;

    public OpportunitiesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpportunityCreated(String companyName) {
        waitUntilAllOpportunitiesAvailable();
        for (WebElement element : opportunitiesTitleElement) {
            if (element.getText().contains(companyName)) {
                return true;
            }
        }
        return false;
    }

    public void dragOpportunityToProposal(String companyName) {
       isOpportunityCreated(companyName);
       for (WebElement element : opportunitiesTitleElement) {
           if (element.getText().contains(companyName)) {
               dragElement(element,proposalSentColumnElement);
           }
       }
    }

    public void waitUntilAllOpportunitiesAvailable() {
        try {
            waitUntilAllElementsVisible(opportunitiesTitleElement, 5);
        } catch (TimeoutException e) {
            refreshPage();
            PageFactory.initElements(driver, OpportunitiesPage.class);
            waitUntilAllElementsVisible(opportunitiesTitleElement, 6);
        }
    }

    public boolean isOpportunityDragged(String companyName) {
        waitUntilAllOpportunitiesAvailable();
        for (WebElement element : proposalSentOpportunitiesTitleElement) {
            if (element.getText().contains(companyName)) {
                return true;
            }
        }
        return false;
    }
}
