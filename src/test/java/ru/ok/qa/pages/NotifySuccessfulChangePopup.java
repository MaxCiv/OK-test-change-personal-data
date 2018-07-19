package ru.ok.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Notification popup about user's personal data successful change.
 */
public class NotifySuccessfulChangePopup extends AbstractPage {

    @FindBy(id = "notifyPanelId")
    private WebElement popup;

    @FindBy(id = "buttonId_button_close")
    private WebElement closeButton;

    public NotifySuccessfulChangePopup(WebDriver driver) {
        super(driver);
    }

    public boolean isPopupPresent() {
        return isElementPresent(popup);
    }

    public void clickCloseButton() {
        closeButton.click();
    }
}
