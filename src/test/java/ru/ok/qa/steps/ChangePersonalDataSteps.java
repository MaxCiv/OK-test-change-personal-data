package ru.ok.qa.steps;

import org.junit.Assert;
import ru.ok.qa.models.User;
import ru.ok.qa.pages.ChangePersonalDataForm;
import ru.ok.qa.pages.NotifySuccessfulChangePopup;
import ru.ok.qa.pages.SettingsMainPage;

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
        changePersonalDataForm.clickSaveButton();

        Assert.assertTrue("Notify panel did not appear.", notifySuccessfulChangePopup.isPopupPresent());
        notifySuccessfulChangePopup.clickCloseButton();
    }

    public String getPersonalDataText() {
        return settingsMainPage.getPersonalDataText();
    }
}
