package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.picocontainer.PicoFactory;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        publish = true,
        stepNotifications = true,
        tags = "@Run",
        features = "@target/failedScenarios.txt",
        glue = "steps",
        plugin = {
                "pretty"
        },
        objectFactory = PicoFactory.class
)
public class RerunTestRunner {
}
