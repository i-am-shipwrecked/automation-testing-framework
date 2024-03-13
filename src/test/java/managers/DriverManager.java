package managers;

import config.Configuration;
import config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverManager {
    private static WebDriver driver;

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        Configuration config = ConfigurationManager.loadConfiguration("src/test/resources/test.properties");

        String browser = config.getProperty("browser.type");
        if (browser == null) {
            throw new IllegalArgumentException("Browser type is not specified in the configuration file.");
        }

        if (browser.equalsIgnoreCase("chrome")) {
            String chromedriverVersion = config.getProperty("chromedriver.version");
            if (chromedriverVersion == null) {
                chromedriverVersion = "121";
            }
            String webdriverPath = config.getProperty("webdriver.path");
            if (webdriverPath == null) {
                throw new IllegalArgumentException("Webdriver path is not specified in the configuration file.");
            }
            String chromedriverPath = webdriverPath + "/chromedriver" + chromedriverVersion + ".exe";
            System.setProperty("webdriver.chrome.driver", chromedriverPath);
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            String webdriverPath = config.getProperty("webdriver.path");
            if (webdriverPath == null) {
                throw new IllegalArgumentException("Webdriver path is not specified in the configuration file.");
            }
            String geckodriverPath = webdriverPath + "/geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", geckodriverPath);
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser \"" + browser + "\" not supported.");
        }

        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver = null;
            }
        }
    }
}
