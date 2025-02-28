package base;

import generalUtility.utilities.BrowserUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import pages.dashboard.HomePage;
import pages.dashboard.ProfilePage;
import pages.login.LoginPage;
import testListners.TestListeners;

@Listeners(TestListeners.class)
public class BaseTest extends BrowserUtils {
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ProfilePage profilePage;


    @BeforeTest
    public void setup() {
        launchBrowser(System.getProperty("browser"));
        loginPage = launchUrl();
        homePage = loginPage.performLogin();
    }


    @AfterTest
    public void tearDown() {
        profilePage = homePage.goToProfile();
        profilePage.logOut();
        quitBrowser();
    }
}
