package runner;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.picocontainer.PicoFactory;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        publish = true,
        stepNotifications = true,
        tags = "@projects",
        features = "src/test/resources/features/api",
        glue = "steps",
        plugin = {
                "pretty",
                "html:target/serenity-reports/serenity-html-report",
                "json:target/serenity-reports/SerenityTestReport.json",
                "rerun:target/serenity-reports/rerun.txt",
                "rerun:target/failedScenarios.txt"
        },
        objectFactory = PicoFactory.class
)
public class ApiTestsRunner {

}
