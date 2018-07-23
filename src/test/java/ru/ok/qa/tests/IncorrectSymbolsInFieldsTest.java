package ru.ok.qa.tests;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.ok.qa.models.User;
import ru.ok.qa.utils.UserCreator;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IncorrectSymbolsInFieldsTest extends ChangePersonalDataTest {

    @Parameterized.Parameters(name = "{index}:symbols({0})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"."}, {"<!--@/*$%^&#*/()?>"}, {"\""}, {"'"}, {"`"}, {"|"}, {"/"}, {"\\"}, {","}, {";"}, {":"},
                {"<"}, {">"}, {"^"}, {"*"}, {"?"}, {"«"}, {"»"}, {"["}, {"]"}, {"~"}, {"!"}, {"@"}, {"%"}, {"&"},
                {"$"}, {"^"}, {"#"}, {"<script>alert(\"xss!\")</script>"}, {"DROP TABLE user;"},
                {"<input onclick=\"javascript:alert('xss');\">"}, {"<input></input>"}
        });
    }

    private static final User prevUser = UserCreator.getDefaultPreviousUser();

    private String wrongSymbols;

    public IncorrectSymbolsInFieldsTest(String wrongSymbols) {
        this.wrongSymbols = wrongSymbols;
    }

    @BeforeClass
    public static void setUp() {
        driver.get(URL_SETTINGS_PAGE);
        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.fillChangePersonalDataForm(prevUser);
    }

    @Test
    public void incorrectSymbolsInFields() {
        changePersonalDataSteps.setName(wrongSymbols);
        changePersonalDataSteps.setSurname(wrongSymbols);
        changePersonalDataSteps.setResidenceCity(wrongSymbols);
        changePersonalDataSteps.closeResidenceSuggest();
        changePersonalDataSteps.setBirthCity(wrongSymbols);
        changePersonalDataSteps.closeBirthSuggest();
        changePersonalDataSteps.clickSaveButton();

        changePersonalDataSteps.checkNameErrorLabelIsPresent();
        changePersonalDataSteps.checkSurnameErrorLabelIsPresent();
        changePersonalDataSteps.checkResidenceCityErrorLabelIsPresent();
        changePersonalDataSteps.checkBirthCityErrorLabelIsPresent();
    }
}
