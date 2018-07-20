package ru.ok.qa.steps;

import org.junit.Assert;
import ru.ok.qa.models.Gender;
import ru.ok.qa.models.User;
import ru.ok.qa.pages.ChangePersonalDataForm;
import ru.ok.qa.pages.NotifySuccessfulChangePopup;
import ru.ok.qa.pages.SettingsMainPage;

import java.util.Calendar;

public class ChangePersonalDataSteps {

    private SettingsMainPage settingsMainPage;
    private ChangePersonalDataForm changePersonalDataForm;
    private NotifySuccessfulChangePopup notifySuccessfulChangePopup;

    public ChangePersonalDataSteps(SettingsMainPage settingsMainPage, ChangePersonalDataForm changePersonalDataForm, NotifySuccessfulChangePopup notifySuccessfulChangePopup) {
        this.settingsMainPage = settingsMainPage;
        this.changePersonalDataForm = changePersonalDataForm;
        this.notifySuccessfulChangePopup = notifySuccessfulChangePopup;
    }

    public void openChangePersonalDataForm() {
        settingsMainPage.clickChangePersonalData();
        Assert.assertTrue("Change personal data form did not appear.", changePersonalDataForm.isFormPresent());
    }

    public void fillChangePersonalDataForm(User user) {
        changePersonalDataForm.setName(user.getName());
        changePersonalDataForm.setSurname(user.getSurname());
        changePersonalDataForm.setBirthDate(user.getBirthDate());
        changePersonalDataForm.setGender(user.getGender());

        changePersonalDataForm.setResidenceCity(user.getResidenceCity().getFullName());
        Assert.assertTrue("Residence suggests did not appear.", changePersonalDataForm.isResidenceSuggestsPresent());
        changePersonalDataForm.selectResidenceSuggest(user.getResidenceCity().getShortName());

        changePersonalDataForm.setBirthCity(user.getBirthCity().getFullName());
        Assert.assertTrue("Birth city suggests did not appear.", changePersonalDataForm.isBirthCitySuggestsPresent());
        changePersonalDataForm.selectBirthSuggest(user.getBirthCity().getShortName());
    }

    public void saveChangedPersonalData() {
        Assert.assertTrue("Save button is not available.", changePersonalDataForm.isSaveButtonPresent());
        changePersonalDataForm.clickSaveButton();

        Assert.assertTrue("Notify panel did not appear.", notifySuccessfulChangePopup.isPopupPresent());
        notifySuccessfulChangePopup.clickCloseButton();
    }

    public void cancelChangingPersonalData() {
        changePersonalDataForm.clickCancelButton();
    }

    public void clickSaveButton() {
        Assert.assertTrue("Save button is not available.", changePersonalDataForm.isSaveButtonPresent());
        changePersonalDataForm.clickSaveButton();
    }

    public String getPersonalDataText() {
        return settingsMainPage.getPersonalDataText();
    }

    public void setName(String name) {
        changePersonalDataForm.setName(name);
    }

    public void setSurname(String surname) {
        changePersonalDataForm.setSurname(surname);
    }

    public void setBirthDate(Calendar birthDate) {
        changePersonalDataForm.setBirthDate(birthDate);
    }

    public void setBirthDate(int birthDay, int birthMonth, int birthYear) {
        changePersonalDataForm.setBirthDate(birthDay, birthMonth, birthYear);
    }

    public void deselectBirthDay() {
        changePersonalDataForm.deselectBirthDay();
    }

    public void deselectBirthMonth() {
        changePersonalDataForm.deselectBirthMonth();
    }

    public void deselectBirthYear() {
        changePersonalDataForm.deselectBirthYear();
    }

    public void setGender(Gender gender) {
        changePersonalDataForm.setGender(gender);
    }

    public void setResidenceCity(String cityFullName) {
        changePersonalDataForm.setResidenceCity(cityFullName);
    }

    public void setBirthCity(String cityFullName) {
        changePersonalDataForm.setBirthCity(cityFullName);
    }

    public void selectResidenceSuggest(String cityShortName) {
        Assert.assertTrue("Residence suggests did not appear.", changePersonalDataForm.isResidenceSuggestsPresent());
        changePersonalDataForm.selectResidenceSuggest(cityShortName);
    }

    public void selectBirthSuggest(String cityShortName) {
        Assert.assertTrue("Birth city suggests did not appear.", changePersonalDataForm.isBirthCitySuggestsPresent());
        changePersonalDataForm.selectBirthSuggest(cityShortName);
    }

    public void closeResidenceSuggest() {
        changePersonalDataForm.closeResidenceSuggest();
        Assert.assertTrue("Residence suggests did not disappear.", changePersonalDataForm.isResidenceSuggestsNotPresent());
    }

    public void closeBirthSuggest() {
        changePersonalDataForm.closeBirthSuggest();
        Assert.assertTrue("Birth suggests did not disappear.", changePersonalDataForm.isBirthCitySuggestsNotPresent());
    }

    public void checkNameErrorLabelIsPresent() {
        Assert.assertTrue("Name error label did not appear.", changePersonalDataForm.isNameErrorLabelPresent());
    }

    public void checkSurnameErrorLabelIsPresent() {
        Assert.assertTrue("Surname error label did not appear.", changePersonalDataForm.isSurnameErrorLabelPresent());
    }

    public void checkBirthDateErrorLabelIsPresent() {
        Assert.assertTrue("Birth date error label did not appear.", changePersonalDataForm.isBirthDateErrorLabelPresent());
    }

    public void checkResidenceCityErrorLabelIsPresent() {
        Assert.assertTrue("Residence city error label did not appear.", changePersonalDataForm.isResidenceCityErrorLabelPresent());
    }

    public void checkBirthCityErrorLabelIsPresent() {
        Assert.assertTrue("Birth city error label did not appear.", changePersonalDataForm.isBirthCityErrorLabelPresent());
    }

    public String getNameText() {
        return changePersonalDataForm.getNameText();
    }

    public String getSurnameText() {
        return changePersonalDataForm.getSurnameText();
    }

    public String getResidenceCityText() {
        return changePersonalDataForm.getResidenceCityText();
    }

    public String getBirthCityText() {
        return changePersonalDataForm.getBirthCityText();
    }
}
