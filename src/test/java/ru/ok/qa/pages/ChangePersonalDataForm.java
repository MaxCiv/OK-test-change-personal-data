package ru.ok.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ok.qa.models.Gender;
import ru.ok.qa.utils.ConfigProperties;

import java.util.Calendar;

public class ChangePersonalDataForm extends AbstractPage {

    private final int timeOutInSeconds = Integer.parseInt(ConfigProperties.getProperty(ConfigProperties.KEY_IMPLICITLY_WAIT_TIME_SEC));

    @FindBy(id = "hook_Form_PopLayerEditUserProfileNewForm")
    private WebElement form;

    @FindBy(id = "field_name")
    private WebElement nameField;

    @FindBy(id = "field_surname")
    private WebElement surnameField;

    @FindBy(id = "field_bday")
    private WebElement birthDayField;

    @FindBy(id = "field_bmonth")
    private WebElement birthMonthField;

    @FindBy(id = "field_byear")
    private WebElement birthYearField;

    @FindBy(id = "field_gender_1")
    private WebElement maleField;

    @FindBy(id = "field_gender_2")
    private WebElement femaleField;

    @FindBy(id = "field_citySugg_SearchInput")
    private WebElement residenceCityField;

    @FindBy(id = "field_cityBSugg_SearchInput")
    private WebElement birthCityField;

    @FindBy(name = "button_savePopLayerEditUserProfileNew")
    private WebElement saveButton;

    @FindBy(name = "button_cancelPopLayerEditUserProfileNew")
    private WebElement cancelButton;

    @FindBy(id = "citySugg_SuggestItems")
    private WebElement residenceCitySuggests;

    @FindBy(id = "cityBSugg_SuggestItems")
    private WebElement birthCitySuggests;

    //TODO более оптимальный способ?
    private String cityResidenceSuggests = "//*[@id='citySugg_SuggestItems']";
    private String cityBirthSuggests = "//*[@id='cityBSugg_SuggestItems']";
    private String cityResidenceSuggest = cityResidenceSuggests + "/li[.//*[text()='%s']]";
    private String cityBirthSuggest = cityBirthSuggests + "/li[.//*[text()='%s']]";

    public ChangePersonalDataForm(WebDriver driver) {
        super(driver);
    }

    public boolean isFormPresent() {
        new WebDriverWait(driver, timeOutInSeconds).until(webDriver -> form.isDisplayed());
        return form.isDisplayed();
    }

    public void setName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void setSurname(String surname) {
        surnameField.clear();
        surnameField.sendKeys(surname);
    }

    public void setBirthDate(Calendar birthDate) {
        new Select(birthDayField).selectByValue(String.valueOf(birthDate.get(Calendar.DAY_OF_MONTH)));
        new Select(birthMonthField).selectByValue(String.valueOf(birthDate.get(Calendar.MONTH) + 1));
        new Select(birthYearField).selectByValue(String.valueOf(birthDate.get(Calendar.YEAR)));
    }

    public void setGender(Gender gender) {
        switch (gender) {
            case FEMALE:
                femaleField.click();
                break;
            default:
                maleField.click();
                break;
        }
    }

    public void setResidenceCity(String city) {
        residenceCityField.clear();
        residenceCityField.sendKeys(city);
    }

    public void setBirthCity(String city) {
        birthCityField.clear();
        birthCityField.sendKeys(city);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public boolean isResidenceSuggestsPresent() {
        new WebDriverWait(driver, timeOutInSeconds).until(webDriver -> residenceCitySuggests.isDisplayed());
        return residenceCitySuggests.isDisplayed();
    }

    public boolean isBirthCitySuggestsPresent() {
        new WebDriverWait(driver, timeOutInSeconds).until(webDriver -> birthCitySuggests.isDisplayed());
        return birthCitySuggests.isDisplayed();
    }

    public void selectResidenceSuggest(String city) {
        String locator = String.format(cityResidenceSuggest, city);
        driver.findElement(By.xpath(locator)).click();
    }

    public void selectBirthSuggest(String city) {
        String locator = String.format(cityBirthSuggest, city);
        driver.findElement(By.xpath(locator)).click();
    }
}
