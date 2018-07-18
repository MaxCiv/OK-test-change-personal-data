package ru.ok.qa.tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import ru.ok.qa.model.City;
import ru.ok.qa.model.Gender;
import ru.ok.qa.model.User;
import ru.ok.qa.pages.ChangePersonalDataModalPage;
import ru.ok.qa.pages.LoginPage;
import ru.ok.qa.pages.NotifySuccessfulChangePanel;
import ru.ok.qa.pages.SettingsMainPage;
import ru.ok.qa.utils.ConfigProperties;
import ru.ok.qa.utils.WebDriverFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ChangePersonalDataTest {

    private static final String URL_LOGIN_PAGE = "https://ok.ru";
    private static final String URL_SETTINGS_PAGE = "https://ok.ru/settings";

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static SettingsMainPage settingsMainPage;
    private static ChangePersonalDataModalPage personalDataModalPage;
    private static NotifySuccessfulChangePanel notifySuccessfulChangePanel;

    @BeforeClass
    public static void setUpBeforeClass() {
        driver = WebDriverFactory.newInstance(WebDriverFactory.FIREFOX);
        loginPage = new LoginPage(driver);
        settingsMainPage = new SettingsMainPage(driver);
        personalDataModalPage = new ChangePersonalDataModalPage(driver);
        notifySuccessfulChangePanel = new NotifySuccessfulChangePanel(driver);

        driver.get(URL_LOGIN_PAGE);
        loginPage.setLogin(ConfigProperties.getProperty(ConfigProperties.KEY_LOGIN));
        loginPage.setPassword(ConfigProperties.getProperty(ConfigProperties.KEY_PASSWORD));
        loginPage.clickLoginButton();
    }

    @Before
    public void setUp() {
        driver.get(URL_SETTINGS_PAGE);
    }

    @Test
    public void userLogin() {
        settingsMainPage.clickChangePersonalData();

        Assert.assertTrue("Personal data modal form did not appear after waiting.", personalDataModalPage.isFormPresent());

        User prevUser = getPreviousUser();
        personalDataModalPage.setName(prevUser.getName());
        personalDataModalPage.setSurname(prevUser.getSurname());
        personalDataModalPage.setBirthDate(prevUser.getBirthDate());
        personalDataModalPage.setGender(prevUser.getGender());

        personalDataModalPage.setResidenceCity(prevUser.getResidenceCity().getFullName());
        Assert.assertTrue("Residence suggests did not appear after waiting.", personalDataModalPage.isResidenceSuggestsPresent());
        personalDataModalPage.selectResidenceSuggest(prevUser.getResidenceCity().getShortName());

        personalDataModalPage.setBirthCity(prevUser.getBirthCity().getFullName());
        Assert.assertTrue("Birth city suggests did not appear after waiting.", personalDataModalPage.isBirthCitySuggestsPresent());
        personalDataModalPage.selectBirthSuggest(prevUser.getBirthCity().getShortName());

        personalDataModalPage.clickSaveButton();

        Assert.assertTrue("Notify panel did not appear after waiting.", notifySuccessfulChangePanel.isPanelPresent());
        notifySuccessfulChangePanel.clickCloseButton();

        Assert.assertEquals("Personal data on settings page does not match to user object.",
                settingsMainPage.getPersonalDataText(), prevUser.formPersonalDataDescription());

        User newUser = getNewUser();
    }

    @AfterClass
    public static void tearDownAfterClass() {
//        logout
//        if (driver != null) {
//            driver.quit();
//        }
    }

    private User getPreviousUser() {
        User user = new User();
        user.setName("Букер");
        user.setSurname("ДеВитт");
        user.setBirthDate(new GregorianCalendar(1974, Calendar.APRIL, 19));
        user.setGender(Gender.MALE);
        user.setResidenceCity(new City("Санкт-Петербург", "Санкт-Петербург, Россия"));
        user.setBirthCity(new City("Петрозаводск", "Петрозаводск, Россия"));
        return user;
    }

    private User getNewUser() {
        User user = new User();
        user.setName("Захари");
        user.setSurname("Комсток");
        user.setBirthDate(new GregorianCalendar(1992, Calendar.DECEMBER, 29));
        user.setGender(Gender.MALE);
        user.setResidenceCity(new City("Москва", "Москва, Россия"));
        user.setBirthCity(new City("Москва", "Москва, Россия"));
        return user;
    }
}
