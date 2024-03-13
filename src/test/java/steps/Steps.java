package steps;

import config.LoggerConfigurator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.DriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import utils.PageFactory;

import java.io.InputStream;
import java.util.Properties;

public class Steps {
    private WebDriver driver = DriverManager.getDriver();
    PageFactory pageFactory = new PageFactory(driver);
    private static final Logger LOGGER = LoggerConfigurator.getLogger();

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
}
