package ru.ok.qa.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ok.qa.models.City;
import ru.ok.qa.models.User;
import ru.ok.qa.utils.UserCreator;

public class CorrectChangePersonalDataTest extends ChangePersonalDataTest {

    private static final int NAME_TEXT_MAX_LENGTH = 16;
    private static final int SURNAME_TEXT_MAX_LENGTH = 24;
    private static final int CITY_TEXT_MAX_LENGTH = 80;

    private final User prevUser = UserCreator.getDefaultPreviousUser();

    @Before
    public void setUp() {
        driver.get(URL_SETTINGS_PAGE);
        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.fillChangePersonalDataForm(prevUser);
        changePersonalDataSteps.saveChangedPersonalData();

        Assert.assertEquals("Personal data on settings page does not match to user object.",
                changePersonalDataSteps.getPersonalDataText(), prevUser.formPersonalDataDescription());
    }

    @Test
    public void correctChangePersonalData() {
        User newUser = UserCreator.getDefaultNewUser();
        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.fillChangePersonalDataForm(newUser);
        changePersonalDataSteps.saveChangedPersonalData();

        Assert.assertEquals("Personal data on settings page does not match to user object.",
                changePersonalDataSteps.getPersonalDataText(), newUser.formPersonalDataDescription());
    }

    @Test
    public void correctChangePersonalDataWithoutBirthCity() {
        User newUser = UserCreator.getDefaultNewUser();
        newUser.setBirthCity(new City("", ""));
        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.setName(newUser.getName());
        changePersonalDataSteps.setSurname(newUser.getSurname());
        changePersonalDataSteps.setBirthDate(newUser.getBirthDate());
        changePersonalDataSteps.setGender(newUser.getGender());
        changePersonalDataSteps.setResidenceCity(newUser.getResidenceCity().getFullName());
        changePersonalDataSteps.selectResidenceSuggest(newUser.getResidenceCity().getShortName());
        changePersonalDataSteps.setBirthCity("");
        changePersonalDataSteps.saveChangedPersonalData();

        Assert.assertEquals("Personal data on settings page does not match to user object.",
                changePersonalDataSteps.getPersonalDataText(), newUser.formPersonalDataDescription());
    }

    @Test
    public void correctCancelChangingPersonalData() {
        User newUser = UserCreator.getDefaultNewUser();
        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.fillChangePersonalDataForm(newUser);
        changePersonalDataSteps.cancelChangingPersonalData();

        Assert.assertEquals("Personal data on settings page does not match to user object.",
                changePersonalDataSteps.getPersonalDataText(), prevUser.formPersonalDataDescription());
    }

    @Test
    public void fieldsWithCertainNumberOfCharacters() {
        String tooLongWord = "aaaaaaaaaaaaaaaaaaaaaaaaaaaa";  // 28 chars

        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.setName(tooLongWord);
        changePersonalDataSteps.setSurname(tooLongWord);
        changePersonalDataSteps.setResidenceCity(tooLongWord + tooLongWord + tooLongWord);
        changePersonalDataSteps.closeResidenceSuggest();
        changePersonalDataSteps.setBirthCity(tooLongWord + tooLongWord + tooLongWord);
        changePersonalDataSteps.closeBirthSuggest();

        Assert.assertEquals("Name field has changed 'maxlength' attribute.",
                NAME_TEXT_MAX_LENGTH, changePersonalDataSteps.getNameText().length());
        Assert.assertEquals("Surname field has changed 'maxlength' attribute.",
                SURNAME_TEXT_MAX_LENGTH, changePersonalDataSteps.getSurnameText().length());
        Assert.assertEquals("ResidenceCity field has changed 'maxlength' attribute.",
                CITY_TEXT_MAX_LENGTH, changePersonalDataSteps.getResidenceCityText().length());
        Assert.assertEquals("BirthCity field has changed 'maxlength' attribute.",
                CITY_TEXT_MAX_LENGTH, changePersonalDataSteps.getBirthCityText().length());
    }
}
