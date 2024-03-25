package steps;;

import com.codeborne.selenide.Configuration;
import config.LoggerConfigurator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.DriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.EmailSender;
import utils.VideoRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Hooks {
    private WebDriver driver = DriverManager.getDriver();
    private static final Logger LOGGER = LoggerConfigurator.getLogger();
    private VideoRecorder recorder;

    @Before
    public void setup() throws IOException, AWTException {
        driver = DriverManager.getDriver();
        Configuration.startMaximized = true;
        recorder = new VideoRecorder();
        recorder.startRecording();
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            recorder.stopRecording();
            if (!scenario.isFailed()) {
                File videoFile = recorder.getVideoFile();
                if (videoFile != null && videoFile.exists()) {
                    videoFile.delete();
                }
            } else {
                String subject = "Failed Test Report";
                String body = "Test Scenario: " + scenario.getName() + " has failed.";
                String recipient = "idontusecocaine@gmail.com";
                EmailSender.sendEmail(recipient, subject, body);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DriverManager.quitDriver();
            } catch (Exception e) {
                System.out.println("Browser did not close properly.");
            }
        }
    }
}
