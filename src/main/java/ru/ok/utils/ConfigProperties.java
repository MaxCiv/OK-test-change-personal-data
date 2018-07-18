package ru.ok.utils;

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

    private static Properties PROPERTIES;

    static {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_PATH)) {
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (FileNotFoundException e) {
            LOG.error("You need to create 'src/test/resources/config.properties' file!\n" + e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private ConfigProperties() {
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
