package anhtester.com.keywords;

import anhtester.com.drivers.DriverManager;
import static anhtester.com.drivers.DriverManager.*; // thêm từ khóa "stati"c và ".*" ở cưới để k cần thông qua class
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WebUI {

    private static int EXPLICIT_WAIT_TIMEOUT = 10;
    private static int WAIT_PAGELOAD_TIMEOUT = 30;
 

    public void hoverOnElement(By by) {
        waitForElementVisible(by);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(getElement(by));
        logConsole("Hover on element " + by);
    }

    public static WebElement highLightElement(By by) {
        waitForElementVisible(by);
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getElement(by));
            sleep(1);
        }
        return getElement(by);
    }

    public void rightClickElement(By by) {
        waitForElementVisible(by);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.contextClick(getElement(by));
        logConsole("Right click on element " + by);
    }

    public static void logConsole(String message) {
        System.out.println(message);

    }

    public static void openURL(String URL) {
        DriverManager.getDriver().get(URL);
        waitForPageLoaded();
        logConsole("Open URL:" + URL);
    }

    public static String getCurrentUrl() {
        waitForPageLoaded();
        logConsole("get CurrentUrl URL: " + DriverManager.getDriver().getCurrentUrl());
        return DriverManager.getDriver().getCurrentUrl();
    }

    public static WebElement getElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static void clickElement(By by) {
        waitForElementVisible(by);
        getElement(by).click();
        logConsole("Click on Element: " + by);
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getElement(by).sendKeys(value);
        logConsole("set Text " + value + "on Element" + by);
    }

    public static String getTextElement(By by) {
        waitForElementVisible(by);
        logConsole("Get text of Element " + by);
        logConsole("=> Text: " + getElement(by).getText());
        return getElement(by).getText();
    }

    public static String getAttributeElement(By by, String attributeName) {
        waitForElementVisible(by);
        logConsole("Get Attribute of Element " + by);
        logConsole("=> Value: " + getElement(by).getAttribute(attributeName));
        return getElement(by).getAttribute(attributeName);
    }

    public static void srollToElement(By by) {
        waitForElemenpresence(by);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(getElement(by)).build().perform();
        //logConsole("Srool to Element " + by);
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElemenpresence(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static Boolean checkElementExist(String xpath) {
        List<WebElement> listElement = DriverManager.getDriver().findElements(By.xpath(xpath));
        if (listElement.size() > 0) {
            System.out.println("Element " + xpath + "exiting.");
            return true;
        } else {
            System.out.println("Element" + xpath + "NOT exits.");
            return false;
        }
    }

    public static boolean verifyElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean verifyElementNOTVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }

    }

    //     * Wait for Page loaded
//     * Chờ đợi trang tải xong (Javascript tải xong)
//     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(WAIT_PAGELOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }
}
