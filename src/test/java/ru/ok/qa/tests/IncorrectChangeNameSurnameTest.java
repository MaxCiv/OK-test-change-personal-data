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
public class IncorrectChangeNameSurnameTest extends ChangePersonalDataTest {

    @Parameterized.Parameters(name = "{index}:symbols({0})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {""}, {"."}, {"<!--@/*$%^&#*/()?>"}, {"\""}, {"'"}, {"`"}, {"|"}, {"/"}, {"\\"}, {","}, {";"}, {":"},
                {"<"}, {">"}, {"^"}, {"*"}, {"?"}, {"«"}, {"»"}, {"["}, {"]"}, {"~"}, {"!"}, {"@"}, {"%"}, {"&"},
                {"$"}, {"^"}, {"#"}, {"<script>alert(\"xss!\")</script>"}, {"DROP TABLE user;"},
                {"<input onclick=\"javascript:alert('xss');\">"}, {"<input></input>"}
        });
    }

    private static final User prevUser = UserCreator.getDefaultPreviousUser();

    private String wrongSymbols;

    public IncorrectChangeNameSurnameTest(String wrongSymbols) {
        this.wrongSymbols = wrongSymbols;
    }

    @BeforeClass
    public static void setUp() {
        driver.get(URL_SETTINGS_PAGE);
        changePersonalDataSteps.openChangePersonalDataForm();
        changePersonalDataSteps.fillChangePersonalDataForm(prevUser);
    }

    @Test
    public void incorrectName() {
        changePersonalDataSteps.setName(wrongSymbols);
        changePersonalDataSteps.clickSaveButton();
        changePersonalDataSteps.checkNameErrorLabelIsPresent();
    }

    @Test
    public void incorrectSurname() {
        changePersonalDataSteps.setSurname(wrongSymbols);
        changePersonalDataSteps.clickSaveButton();
        changePersonalDataSteps.checkSurnameErrorLabelIsPresent();
    }
}
