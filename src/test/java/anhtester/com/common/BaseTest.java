package anhtester.com.common;

import anhtester.com.drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public static void createDriver(@Optional("chrome") String browser) {
        WebDriver driver =  setupDriver(browser);
        System.out.println(driver);
        //Set giá trị driver đã dc khởi tạo vào Threadlocal
        DriverManager.setDriver(driver);
    }

    public static WebDriver setupDriver(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private static WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        System.out.println("Launching Edge browser...");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public static void closeDriver() {
        if(DriverManager.getDriver ()!= null)
        {
            DriverManager.quit();
        }
    }
//    public WebDriver driver;
//
//    @BeforeMethod
//    public void createDriver() {
//        //WebDriverManager.chromedriver().setup();
//        System.out.println("Start Chrome browser");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//
//        //pageLoadTimeout: Chờ đợi Page load xong khoảng thời gian 30s
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
//    }
//
//    @AfterMethod
//    public void closeDriver() {
//        System.out.println("Close Chrome browser");
//        driver.quit();
//    }
}
