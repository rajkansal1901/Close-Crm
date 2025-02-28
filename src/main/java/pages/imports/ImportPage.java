package pages.imports;
import generalUtility.utilities.GeneralUtils;
import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ImportPage extends WebPageUtils {

    @FindBy(css = "[class*=\"ImporterDashboardScreen_actions\"] > div > div:first-child")
    protected WebElement uploadCSVFileElement;

    @FindBy(xpath = "//button[text() = \"Skip unassigned columns\"]")
    protected WebElement skipUnassignedColumnElement;

    @FindBy(id = "SelectToggleButton_ImportField__FieldSelect")
    protected List<WebElement> inputColumnFieldElement;

    @FindBy(xpath = "//*[text() = \"Name\"]")
    protected WebElement assignByNameElement;

    @FindBy(css = "button[class *= \"LeadImporterFooter\"]")
    protected WebElement importLeadNextButtonSelector;

    @FindBy(css = ".IconSVG.IconSVG--fluid")
    protected WebElement greenConfirmationMarkElement;

    @FindBy(xpath = "//span[text() = \"Import finished\"]")
    protected WebElement importPageMessageElement;

    @FindBy(css = "[class *= \"Stepper_stepper\"]")
    protected WebElement importLoadingElement;

    public ImportPage(WebDriver driver) {
        super(driver);
    }

    public void importData(String filePath) {
        waitAndClick(uploadCSVFileElement);
        GeneralUtils.robotUploadFile(filePath);
        waitUntilElementVisibility(skipUnassignedColumnElement, 10);
        waitAndClick(inputColumnFieldElement.get(0));
        moveToElementAndClick(assignByNameElement);
        waitAndClick(skipUnassignedColumnElement);
        waitAndClick(importLeadNextButtonSelector);
        moveToElementAndClick(importLoadingElement);
        waitAndClick(importLeadNextButtonSelector);
        waitUntilElementVisibility(greenConfirmationMarkElement, 50);
    }

    public boolean isImportSuccessFul() {
        waitUntilElementVisibility(importPageMessageElement, 3);
        return importPageMessageElement.getText().contains("Import finished");
    }
}
