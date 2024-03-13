package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    private ConfigurationManager() {}

    public static Configuration loadConfiguration(String configFile) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(configFile)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error loading configuration from file: " + configFile);
        }
        return new Configuration(properties);
    }
}
