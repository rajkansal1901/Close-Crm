package pages;

import generalUtility.utilities.WebPageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.task.TasksPage;

public class SmsPage extends WebPageUtils {

    @FindBy(css = ".InboxHeaderTabs_root_bc7 button:nth-child(5)")
    protected WebElement taskButtonElement;


    public SmsPage(WebDriver driver) {
        super(driver);
    }

    public TasksPage goToTaskPage() {
        goToPage(taskButtonElement);
        return PageFactory.initElements(driver, TasksPage.class);
    }
}
