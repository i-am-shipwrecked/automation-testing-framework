package config;

import java.util.Properties;

public class Configuration {
    private Properties properties;

    Configuration(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public boolean getRetryFailedTests() {
        return Boolean.parseBoolean(properties.getProperty("retryFailedTests", "true"));
    }
}