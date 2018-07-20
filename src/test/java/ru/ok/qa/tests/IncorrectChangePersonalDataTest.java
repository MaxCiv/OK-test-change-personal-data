package ru.ok.qa.tests;

import org.junit.Before;
import org.junit.Test;
import ru.ok.qa.models.User;
import ru.ok.qa.utils.UserCreator;

public class IncorrectChangePersonalDataTest extends ChangePersonalDataTest {

    private final User prevUser = UserCreator.getDefaultPreviousUser();

    /**
     * Opens 'Change personal data form', fills it with default data and doesn't close it.
     */
    @Before
    public void setUp() {
        driver.get(URL_SETTINGS_PAGE);
        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.fillChangePersonalDataForm(prevUser);
    }

    @Test
    public void incorrectBirthDate() {
        // wrong birth day values
        changePersonalDataSteps.setBirthDate(31, 6, 1990);
        changePersonalDataSteps.clickSaveButton();
        changePersonalDataSteps.checkBirthDateErrorLabelIsPresent();

        changePersonalDataSteps.setBirthDate(29, 2, 1995);
        changePersonalDataSteps.clickSaveButton();
        changePersonalDataSteps.checkBirthDateErrorLabelIsPresent();

        // empty fields
        changePersonalDataSteps.setBirthDate(prevUser.getBirthDate());
        changePersonalDataSteps.deselectBirthDay();
        changePersonalDataSteps.clickSaveButton();
        changePersonalDataSteps.checkBirthDateErrorLabelIsPresent();

        changePersonalDataSteps.setBirthDate(prevUser.getBirthDate());
        changePersonalDataSteps.deselectBirthMonth();
        changePersonalDataSteps.clickSaveButton();
        changePersonalDataSteps.checkBirthDateErrorLabelIsPresent();

        changePersonalDataSteps.setBirthDate(prevUser.getBirthDate());
        changePersonalDataSteps.deselectBirthYear();
        changePersonalDataSteps.clickSaveButton();
        changePersonalDataSteps.checkBirthDateErrorLabelIsPresent();
    }

    @Test
    public void emptyRequiredFields() {
        changePersonalDataSteps.setName("    ");
        changePersonalDataSteps.setSurname("    ");
        changePersonalDataSteps.setResidenceCity("    ");
        changePersonalDataSteps.closeResidenceSuggest();
        changePersonalDataSteps.clickSaveButton();
        changePersonalDataSteps.checkNameErrorLabelIsPresent();
        changePersonalDataSteps.checkSurnameErrorLabelIsPresent();
        changePersonalDataSteps.checkResidenceCityErrorLabelIsPresent();

        changePersonalDataSteps.setName("");
        changePersonalDataSteps.setSurname("");
        changePersonalDataSteps.setResidenceCity("");
        changePersonalDataSteps.clickSaveButton();
        changePersonalDataSteps.checkNameErrorLabelIsPresent();
        changePersonalDataSteps.checkSurnameErrorLabelIsPresent();
        changePersonalDataSteps.checkResidenceCityErrorLabelIsPresent();
    }
}
