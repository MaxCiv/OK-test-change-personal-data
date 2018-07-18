package ru.ok.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsMainPage extends AbstractPage {

    @FindBy(css = "[href*='EditUserProfile']")
    private WebElement personalDataBlock;

    @FindBy(css = ".user-settings_i_tx.textWrap")
    private WebElement personalDataText;

    public SettingsMainPage(WebDriver driver) {
        super(driver);
    }

    public void clickChangePersonalData() {
        personalDataBlock.click();
    }

    public String getPersonalDataText() {
        return personalDataText.getText();
    }
}
