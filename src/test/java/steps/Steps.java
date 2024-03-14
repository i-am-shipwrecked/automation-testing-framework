package steps;

import config.LoggerConfigurator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.DriverManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import pages.MainPage;
import utils.PageFactory;
import utils.RetryManager;
import utils.Waiter;

import java.io.InputStream;
import java.util.Properties;

public class Steps {
    private WebDriver driver = DriverManager.getDriver();
    private PageFactory pageFactory = new PageFactory(driver);
    private static final Logger LOGGER = LoggerConfigurator.getLogger();
    private Waiter waiter = Waiter.getInstance();
    MainPage mainPage = new MainPage(driver);

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
    }

    @After
    public void tearDown() {
        try {
            DriverManager.quitDriver();
        } catch (Exception e) {
            System.out.println("браузер не закрылся");
        }
    }

    @After
    public void retryFailedScenario(Scenario scenario) {
        if (scenario.isFailed() && RetryManager.shouldRetry()) {
            RetryManager.incrementRetryCount();
            System.out.println("Retrying failed scenario: " + scenario.getName());
            // Здесь можно реализовать логику перезапуска сценария
        } else {
            RetryManager.resetRetryCount();
        }
    }

    @Given("User is on the page, which you can insert into sql_injector.properties")
    public void userIsOnThePageWhichYouCanInsertIntoSql_injectorProperties() {
    }

    @Given("User choose the page where he wants to start SQL injector tests")
    public void userChooseThePageWhereHeWantsToStartSQLInjectorTests() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("test.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String appUrl = properties.getProperty("app.url");
        LOGGER.info("Test logs");
        driver.get(appUrl);
    }

    @When("User tries to type in a SQL Injection {string} into input field")
    public void userTriesToTypeInASQLInjectionIntoInputField(String sqlInjection) {

    }

    @And("User clicks on Enter button")
    public void userClicksOnEnter() {

    }

    @Then("Verify that your database is not broken =)")
    public void nothingHappens() {
    }

    @When("User clicks on null button")
    public void userClicksOnNullButton() {
        WebElement element = mainPage.getElement();
        if (element != null) {
            element.click();
        } else {
            throw new RuntimeException("The element is null, intentionally failing the test.");
        }
    }


    @Then("Test should be failed and returned")
    public void testShouldBeFailedAndReruned() {
        Assert.assertTrue(mainPage.getElement().isDisplayed());
    }
}
