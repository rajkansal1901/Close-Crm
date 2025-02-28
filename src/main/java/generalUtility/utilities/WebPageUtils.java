package generalUtility.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class WebPageUtils {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions actions;
    protected TakesScreenshot screenshot;

    public WebPageUtils(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        screenshot = (TakesScreenshot) driver;
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void waitAndClickElementAndSendKeys(By locator, String key) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        getElement(locator).sendKeys(key);
    }

    public void waitAndClickElementAndSendKeys(WebElement element, String key) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(key);
    }

    public void goTOPreviousPage() {
        driver.navigate().back();
    }

    public void doSendKeys(By locator, String value) {
        getElement(locator).sendKeys(value);
    }

    public void doSendKeys(WebElement element, String value) {
        element.sendKeys(value);
    }

    public void clickElement(By locator) {
        getElement(locator).click();
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public void fullScreen() {
        driver.manage().window().maximize();
    }

    public void waitAndClick(By locator) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void waitAndClick(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            moveToElementAndClick(element);
        }
    }

    public void waitUntilElementVisibility(By locator, int seconds) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public void waitUntilElementVisibility(WebElement element, int seconds) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementClickable(By locator) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(getElement(locator)));
    }

    public void waitUntilElementClickable(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementReady(By locator, int seconds) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilAllElementsVisible(By locator) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(locator)));
    }

    public void waitUntilAllElementsVisible(List<WebElement> elements) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitUntilAllElementsVisible(List<WebElement> elements, int seconds) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void switchToIframe(String idOrName) {
        driver.switchTo().frame(idOrName);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void moveToElement(By locator) {
        actions.moveToElement(getElement(locator)).perform();
    }

    public void moveToElement(WebElement element) {
        waitUntilElementVisibility(element, 5);
        actions.moveToElement(element).perform();
    }

    public void pressKey(Keys key) {
        actions.keyDown(key).release().perform();
    }


    public void moveToElementAndClick(By locator) {
        moveToElement(getElement(locator));
        actions.click(getElement(locator)).perform();
    }

    public void moveToElementAndClick(WebElement element) {
        moveToElement(element);
        actions.click(element).perform();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void enterData(By locator, String data) {
        actions.moveToElement(getElement(locator)).click().perform();
        char[] chars = data.toCharArray();
        for (char ch : chars) {
            actions.sendKeys(String.valueOf(ch)).build().perform();
            actions.pause(Duration.ofMillis(30)).perform();
        }
    }

    public void enterData(WebElement element, String data) {
        actions.moveToElement(element).click().perform();
        char[] chars = data.toCharArray();
        for (char ch : chars) {
            actions.sendKeys(String.valueOf(ch)).build().perform();
            actions.pause(Duration.ofMillis(30)).perform();
        }
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public String takeScreenshot() {
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        String fileName = GeneralUtils.getUniqueName("screenshot") + ".png";
        try {
            FileUtils.copyFile(file, new File("src\\main\\java\\generalUtility\\screenshots\\" + fileName));
        } catch (IOException e) {
            System.out.println("Failed to Capture Screenshot");
        }
        return fileName;
    }

    public void goToPage(WebElement element) {
        try {
            waitAndClick(element);
        } catch (Exception e) {
            moveToElementAndClick(element);
        }
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("There is some error");
        }
    }

    public void dragElement(WebElement elementToDrag, WebElement destination) {
        actions.clickAndHold(elementToDrag).build().perform();
        actions.moveToElement(destination).build().perform();
        actions.release().build().perform();
    }

    public static String getProperty(String key) {
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream("src/main/java/config/config.properties");
            properties.load(fis);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.out.println("Data not available");
            return null;
        }
    }

}
