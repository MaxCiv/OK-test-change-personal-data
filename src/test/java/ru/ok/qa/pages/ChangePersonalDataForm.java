package ru.ok.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.ok.qa.models.Gender;

import java.util.Calendar;

public class ChangePersonalDataForm extends AbstractPage {

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

    @FindBy(css = ".form_i.form_i__error[data-l='t,name']>span.input-e")
    private WebElement nameErrorLabel;

    @FindBy(css = ".form_i.form_i__error[data-l='t,surname']>span.input-e")
    private WebElement surnameErrorLabel;

    @FindBy(css = ".form_i.form_i__error[data-l='t,birthday']>span.input-e")
    private WebElement birthDateErrorLabel;

    @FindBy(xpath = "//span[text()='Пожалуйста, выберите место проживания из списка']") // oh my god, where is dot?
    private WebElement residenceCityErrorLabel;

    @FindBy(xpath = "//span[text()='Пожалуйста, выберите родной город из списка.']")
    private WebElement birthCityErrorLabel;

    //TODO более оптимальный способ?
    private String cityResidenceSuggests = "//*[@id='citySugg_SuggestItems']";
    private String cityBirthSuggests = "//*[@id='cityBSugg_SuggestItems']";
    private String cityResidenceSuggest = cityResidenceSuggests + "/li[.//*[text()='%s']]";
    private String cityBirthSuggest = cityBirthSuggests + "/li[.//*[text()='%s']]";

    public ChangePersonalDataForm(WebDriver driver) {
        super(driver);
    }

    public boolean isFormPresent() {
        return isElementPresent(form);
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

    public void setBirthDate(int birthDay, int birthMonth, int birthYear) {
        new Select(birthDayField).selectByValue(String.valueOf(birthDay));
        new Select(birthMonthField).selectByValue(String.valueOf(birthMonth));
        new Select(birthYearField).selectByValue(String.valueOf(birthYear));
    }

    public void deselectBirthDay() {
        new Select(birthDayField).selectByValue("");
    }

    public void deselectBirthMonth() {
        new Select(birthMonthField).selectByValue("");
    }

    public void deselectBirthYear() {
        new Select(birthYearField).selectByValue("");
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

    public boolean isSaveButtonPresent() {
        return isElementPresent(saveButton);
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public boolean isCancelButtonPresent() {
        return isElementPresent(cancelButton);
    }

    public boolean isResidenceSuggestsPresent() {
        return isElementPresent(residenceCitySuggests);
    }

    public boolean isBirthCitySuggestsPresent() {
        return isElementPresent(birthCitySuggests);
    }

    public boolean isResidenceSuggestsNotPresent() {
        return isElementNotPresent(residenceCitySuggests);
    }

    public boolean isBirthCitySuggestsNotPresent() {
        return isElementNotPresent(birthCitySuggests);
    }

    public void selectResidenceSuggest(String city) {
        String locator = String.format(cityResidenceSuggest, city);
        driver.findElement(By.xpath(locator)).click();
    }

    public void selectBirthSuggest(String city) {
        String locator = String.format(cityBirthSuggest, city);
        driver.findElement(By.xpath(locator)).click();
    }

    public void closeResidenceSuggest() {
        residenceCityField.click();
        residenceCityField.sendKeys(Keys.TAB);
    }

    public void closeBirthSuggest() {
        birthCityField.click();
        birthCityField.sendKeys(Keys.TAB);
    }

    public boolean isNameErrorLabelPresent() {
        return isElementPresent(nameErrorLabel);
    }

    public boolean isSurnameErrorLabelPresent() {
        return isElementPresent(surnameErrorLabel);
    }

    public boolean isBirthDateErrorLabelPresent() {
        return isElementPresent(birthDateErrorLabel);
    }

    public boolean isResidenceCityErrorLabelPresent() {
        return isElementPresent(residenceCityErrorLabel);
    }

    public boolean isBirthCityErrorLabelPresent() {
        return isElementPresent(birthCityErrorLabel);
    }

    public String getNameText() {
        return nameField.getAttribute("value");
    }

    public String getSurnameText() {
        return surnameField.getAttribute("value");
    }

    public String getResidenceCityText() {
        return residenceCityField.getAttribute("value");
    }

    public String getBirthCityText() {
        return birthCityField.getAttribute("value");
    }
}
