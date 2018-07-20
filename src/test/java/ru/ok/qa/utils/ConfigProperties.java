package ru.ok.qa.utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class ConfigProperties {

    private static final Logger LOG = Logger.getLogger(ConfigProperties.class.getName());
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CHROMEDRIVER = "chromedriver";
    public static final String KEY_GECKODRIVER = "geckodriver";
    public static final String KEY_IEDRIVER = "iedriver";
    public static final String KEY_EDGEDRIVER = "edgedriver";
    public static final String KEY_IMPLICITLY_WAIT_TIME_SEC = "implicitlyWaitTimeSec";
    public static final String KEY_USE_BROWSER = "useBrowser";

    private static Properties properties;

    static {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_PATH)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            LOG.error("You need to create 'src/test/resources/config.properties' file!\n" + e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private ConfigProperties() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
