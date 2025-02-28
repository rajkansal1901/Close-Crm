package pages.report;

import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class ReportPage extends WebPageUtils {

    @FindBy(css = "[aria-label=\"More Options\"]")
    protected WebElement moreOptionButtonElement;

    @FindBy(css = "[rel=\"noopener\"]")
    protected WebElement exportAsCSVButtonElement;


    public ReportPage(WebDriver driver) {
        super(driver);
    }

    public void downloadReportAsCSV() {
        waitAndClick(moreOptionButtonElement);
        waitAndClick(exportAsCSVButtonElement);
    }

    public void isFileDownloaded() {
        File file = new File("downloads");
    }

}
