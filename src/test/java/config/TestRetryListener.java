package config;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.Status;

import java.io.FileWriter;
import java.io.IOException;

public class TestRetryListener implements ConcurrentEventListener {
    private static final int MAX_RETRY_COUNT = 3;
    private int retryCount = 0;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
    }

    private void onTestCaseFinished(TestCaseFinished event) {
        if (event.getResult().getStatus().is(Status.FAILED) && retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            System.out.println("Retrying test " + event.getTestCase().getName() + " for the " + retryCount + " time(s).");
            writeToRerunFile(event.getTestCase().getUri().toString() + ":" + event.getTestCase().getLocation().getLine());
        }
    }

    private void writeToRerunFile(String testCase) {
        try (FileWriter writer = new FileWriter("target/serenity-reports/rerun.txt", true)) {
            writer.write(testCase + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
