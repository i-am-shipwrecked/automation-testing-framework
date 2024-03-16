package runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class ParallelTestRunner {
}
