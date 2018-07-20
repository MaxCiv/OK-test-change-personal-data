package ru.ok.qa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public final class WebDriverFactory {

    public static final String CHROME = "chrome";
    public static final String INTERNET_EXPLORER = "ie";
    public static final String EDGE = "edge";
    public static final String FIREFOX = "firefox";

    private WebDriverFactory() {
    }

    public static WebDriver newInstance(String browser) {
        WebDriver driver;
        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty(ConfigProperties.KEY_CHROMEDRIVER));
                driver = new ChromeDriver();
                break;
            case INTERNET_EXPLORER:
                System.setProperty("webdriver.ie.driver", ConfigProperties.getProperty(ConfigProperties.KEY_IEDRIVER));
                driver = new InternetExplorerDriver();
                break;
            case EDGE:
                System.setProperty("webdriver.edge.driver", ConfigProperties.getProperty(ConfigProperties.KEY_EDGEDRIVER));
                driver = new EdgeDriver();
                break;
            default:
                System.setProperty("webdriver.gecko.driver", ConfigProperties.getProperty(ConfigProperties.KEY_GECKODRIVER));
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ConfigProperties.getProperty(ConfigProperties.KEY_IMPLICITLY_WAIT_TIME_SEC)), TimeUnit.SECONDS);
        return driver;
    }
}
