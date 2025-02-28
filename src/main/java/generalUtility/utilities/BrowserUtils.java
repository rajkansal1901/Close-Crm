package generalUtility.utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import pages.login.LoginPage;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;

public class BrowserUtils {
    protected WebDriver driver;
    String browser;

    public BrowserUtils() {
        driver = null;
        browser = System.getProperty("browser", "Chrome");
    }


    public WebDriver launchBrowser(String browser) {
        browser = this.browser;
        switch (browser.trim().toLowerCase()) {
            case ("chrome"):
                ChromeOptions chromeOptions = new ChromeOptions();
                HashMap<String, Object> chromePrefs = new HashMap<>();
                String projectDownload = Paths.get("src/main/java/generalUtility/downloads").toAbsolutePath().toString();
                chromePrefs.put("download.default_directory", projectDownload);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                chromeOptions.addArguments("start-maximized", "disable-popup-blocking");
                return driver = new ChromeDriver(chromeOptions);

            case ("edge"):
                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    EdgeOptions edgeOption = new EdgeOptions();
                    edgeOption.addArguments("--start-maximized");
                    return driver = new EdgeDriver(edgeOption);
                }

            case ("firefox"):
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                return driver = new FirefoxDriver();

            case ("safari"):
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    return driver = new SafariDriver();
                }
        }
        System.out.println("Browser is not Supported :" + browser);
        return null;
    }

    public LoginPage launchUrl() {
        driver.get(WebPageUtils.getProperty("url"));
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public void pageLoadOut() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    public void closeBrowser() {
        driver.close();
    }

    public void quitBrowser() {
        driver.quit();
    }

    @DataProvider(name = "lead")
    public Object[][] leadData() {
        Object[][] data = { {"John Ltd", "John Rambo"}, {"Paul Ltd", "Paul Sterling"}, {"Lee Ltd", "Lee Yuan"}, {"Blair Ltd", "Blair Pollock"} };
        return data;
    }
}
