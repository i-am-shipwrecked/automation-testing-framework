package runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/java/features",
//        glue = "steps",
//        plugin = {"pretty", "html:target/cucumber-reports"},
//        tags = "@Run",
//        objectFactory = PicoFactory.class,
//        monochrome = true
//)
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@CucumberOptions(
        // Убедитесь, что здесь нет конфликтующих настроек ObjectFactory
        plugin = {"pretty", "html:target/cucumber-report.html"}
        // Другие настройки Cucumber, если они вам нужны
)
public class ParallelTestRunner {
}
