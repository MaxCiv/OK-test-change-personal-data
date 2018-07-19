package ru.ok.qa.tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import ru.ok.qa.models.User;
import ru.ok.qa.pages.ChangePersonalDataForm;
import ru.ok.qa.pages.LoginForm;
import ru.ok.qa.pages.NotifySuccessfulChangePopup;
import ru.ok.qa.pages.SettingsMainPage;
import ru.ok.qa.steps.ChangePersonalDataSteps;
import ru.ok.qa.steps.LoginSteps;
import ru.ok.qa.utils.ConfigProperties;
import ru.ok.qa.utils.UserCreator;
import ru.ok.qa.utils.WebDriverFactory;

public class ChangePersonalDataTest {

    private static final String URL_LOGIN_PAGE = "https://ok.ru";
    private static final String URL_SETTINGS_PAGE = "https://ok.ru/settings";

    private static WebDriver driver;

    private static LoginSteps loginSteps;
    private static ChangePersonalDataSteps changePersonalDataSteps;

    @BeforeClass
    public static void setUpBeforeClass() {
        driver = WebDriverFactory.newInstance(WebDriverFactory.FIREFOX);
        loginSteps = new LoginSteps(new LoginForm(driver));
        changePersonalDataSteps = new ChangePersonalDataSteps(new SettingsMainPage(driver),
                new ChangePersonalDataForm(driver), new NotifySuccessfulChangePopup(driver));

        driver.get(URL_LOGIN_PAGE);
        loginSteps.fillLoginForm(ConfigProperties.getProperty(ConfigProperties.KEY_LOGIN),
                ConfigProperties.getProperty(ConfigProperties.KEY_PASSWORD));
        loginSteps.submitLogin();
    }

    @Before
    public void setUp() {
        driver.get(URL_SETTINGS_PAGE);
    }

    @Test
    public void correctChangePersonalData() {
        User prevUser = UserCreator.getDefaultPreviousUser();
        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.fillChangePersonalDataForm(prevUser);
        changePersonalDataSteps.saveChangedPersonalData();

        Assert.assertEquals("Personal data on settings page does not match to user object.",
                changePersonalDataSteps.getPersonalDataText(), prevUser.formPersonalDataDescription());

        User newUser = UserCreator.getDefaultNewUser();
        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.fillChangePersonalDataForm(newUser);
        changePersonalDataSteps.saveChangedPersonalData();

        Assert.assertEquals("Personal data on settings page does not match to user object.",
                changePersonalDataSteps.getPersonalDataText(), newUser.formPersonalDataDescription());
    }

    @AfterClass
    public static void tearDownAfterClass() {
//        if (driver != null) {
//            driver.quit();
//        }
    }
}
