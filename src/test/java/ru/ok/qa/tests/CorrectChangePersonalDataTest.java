package ru.ok.qa.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ok.qa.models.City;
import ru.ok.qa.models.User;
import ru.ok.qa.utils.UserCreator;

public class CorrectChangePersonalDataTest extends ChangePersonalDataTest {

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
        changePersonalDataSteps.setBirthCityEmpty();
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
    public void nameAndSurnameWithCertainNumberOfCharacters() {
        String tooLongWord = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";  // 30 chars
        String longestName = "aaaaaaaaaaaaaaaa";  // 16 chars
        String longestSurname = "aaaaaaaaaaaaaaaaaaaaaaaa";  // 24 chars
        User newUser = UserCreator.getDefaultNewUser();
        newUser.setName(tooLongWord);
        newUser.setSurname(tooLongWord);
        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.fillChangePersonalDataForm(newUser);
        changePersonalDataSteps.saveChangedPersonalData();

        newUser.setName(longestName);
        newUser.setSurname(longestSurname);
        Assert.assertEquals("Personal data on settings page does not match to user object.",
                changePersonalDataSteps.getPersonalDataText(), newUser.formPersonalDataDescription());
    }
}
