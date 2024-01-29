package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
        Properties properties = new Properties();
        try (InputStream input = DriverManager.class.getClassLoader().getResourceAsStream("test.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = properties.getProperty("browser.type");
        if (browser.equalsIgnoreCase("chrome")) {
            String chromedriverVersion = properties.getProperty("chromedriver.version", "121");
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver" + chromedriverVersion + ".exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser \"" + browser + "\" not supported.");
        }

        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
