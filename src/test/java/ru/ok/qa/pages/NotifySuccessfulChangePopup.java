package ru.ok.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ok.qa.utils.ConfigProperties;

/**
 * Notification popup about user's personal data successful change.
 */
public class NotifySuccessfulChangePopup extends AbstractPage {

    private final int timeOutInSeconds = Integer.parseInt(ConfigProperties.getProperty(ConfigProperties.KEY_IMPLICITLY_WAIT_TIME_SEC));

    @FindBy(id = "notifyPanelId")
    private WebElement popup;

    @FindBy(id = "buttonId_button_close")
    private WebElement closeButton;

    public NotifySuccessfulChangePopup(WebDriver driver) {
        super(driver);
    }

    public boolean isPopupPresent() {
        new WebDriverWait(driver, timeOutInSeconds).until(webDriver -> popup.isDisplayed());
        return popup.isDisplayed();
    }

    public void clickCloseButton() {
        closeButton.click();
    }
}
