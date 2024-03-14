package utils;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import utils.RetryManager;
public class Hooks {
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
}
