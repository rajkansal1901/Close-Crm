package pages.task;

import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.contacts.ContactPage;

import java.util.List;

public class TasksPage extends WebPageUtils {

    @FindBy(css = ".inbox-list > div > div > div:nth-child(4) > div")
    protected List<WebElement> allTaskElements;

    @FindBy(css = ".InboxHeader_filter_3fa")
    protected WebElement nameTagElement;

    @FindBy(css = "button  .Btn__text")
    protected WebElement dateCompletedElement;

    @FindBy(css = ".ExpandedItemLayout_titleWrapper_22c h4")
    protected WebElement followUpTitleElement;

    @FindBy(css = "div > div:nth-child(3)> div > span > button")
    protected WebElement markAsDoneButtonElement;

    @FindBy(css = ".inbox-list > div > div > div:nth-child(4)")
    protected List<WebElement> allDoneTaskElements;


    public TasksPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage clickTaskByName(String companyName) {
        refreshPage();
            waitUntilAllElementsVisible(allTaskElements);
        for (WebElement taskElement : allTaskElements) {
            if (taskElement.getText().contains(companyName)) {
                taskElement.click();
            }
        }
        return PageFactory.initElements(driver, ContactPage.class);
    }
    public boolean isDoneTaskAvailable(String companyName) {
        try {
            waitUntilAllElementsVisible(allTaskElements);
        } catch (Exception e) {
            refreshPage();
            waitUntilAllElementsVisible(allTaskElements);
            System.out.println("There is no pending Task Available");
        }
        for (WebElement element : allDoneTaskElements) {
            if (element.getText().contains(companyName)) {
                return true;
            }
        }
        return false;
    }

    public TasksPage refreshTaskPage() {
        driver.navigate().refresh();
        return PageFactory.initElements(driver, TasksPage.class);
    }
}