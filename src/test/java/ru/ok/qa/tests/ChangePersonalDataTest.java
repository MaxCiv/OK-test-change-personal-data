package ru.ok.qa.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.ok.qa.pages.LoginPage;
import ru.ok.utils.ConfigProperties;

import java.util.concurrent.TimeUnit;

public class ChangePersonalDataTest {

    private static final String URL_LOGIN_PAGE = "https://ok.ru";
    private static final String URL_SETTINGS_PAGE = "https://ok.ru/settings";
    private static final int IMPLICITLY_WAIT_TIME_SEC = 5;

    private static WebDriver driver;
    private static LoginPage loginPage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty(ConfigProperties.KEY_CHROMEDRIVER));
        System.setProperty("webdriver.gecko.driver", ConfigProperties.getProperty(ConfigProperties.KEY_GECKODRIVER));
//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
        loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME_SEC, TimeUnit.SECONDS);
        driver.get(URL_LOGIN_PAGE);
    }

    @Test
    public void userLogin() {
        loginPage.inputLogin(ConfigProperties.getProperty(ConfigProperties.KEY_LOGIN));
        loginPage.inputPassword(ConfigProperties.getProperty(ConfigProperties.KEY_PASSWORD));
        loginPage.clickLoginButton();

        driver.get(URL_SETTINGS_PAGE);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
