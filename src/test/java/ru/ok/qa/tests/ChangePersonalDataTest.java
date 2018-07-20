package ru.ok.qa.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import ru.ok.qa.pages.ChangePersonalDataForm;
import ru.ok.qa.pages.LoginForm;
import ru.ok.qa.pages.NotifySuccessfulChangePopup;
import ru.ok.qa.pages.SettingsMainPage;
import ru.ok.qa.steps.ChangePersonalDataSteps;
import ru.ok.qa.steps.LoginSteps;
import ru.ok.qa.utils.ConfigProperties;
import ru.ok.qa.utils.WebDriverFactory;

public abstract class ChangePersonalDataTest {

    protected static final String URL_LOGIN_PAGE = "https://ok.ru";
    protected static final String URL_SETTINGS_PAGE = "https://ok.ru/settings";

    protected static WebDriver driver;

    protected static LoginSteps loginSteps;
    protected static ChangePersonalDataSteps changePersonalDataSteps;

    @BeforeClass
    public static void setUpBeforeClass() {
        driver = WebDriverFactory.newInstance(ConfigProperties.getProperty(ConfigProperties.KEY_USE_BROWSER));

        loginSteps = new LoginSteps(new LoginForm(driver));
        changePersonalDataSteps = new ChangePersonalDataSteps(new SettingsMainPage(driver),
                new ChangePersonalDataForm(driver), new NotifySuccessfulChangePopup(driver));

        driver.get(URL_LOGIN_PAGE);
        loginSteps.fillLoginForm(ConfigProperties.getProperty(ConfigProperties.KEY_LOGIN),
                ConfigProperties.getProperty(ConfigProperties.KEY_PASSWORD));
        loginSteps.submitLogin();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
