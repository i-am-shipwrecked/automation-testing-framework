package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.picocontainer.PicoFactory;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        publish = true,
        stepNotifications = true,
        tags = "@Run",
        features = "src/test/java/features",
        glue = "steps",
        plugin = {
                "pretty",
                "config.TestRetryListener",
                "html:target/serenity-reports/serenity-html-report",
                "json:target/serenity-reports/SerenityTestReport.json",
                "rerun:target/serenity-reports/rerun.txt",
                "rerun:target/failedScenarios.txt"
        },
        objectFactory = PicoFactory.class
)
public class Runner {
}
