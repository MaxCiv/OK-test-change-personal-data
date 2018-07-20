package ru.ok.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ok.qa.utils.ConfigProperties;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected final int timeOutInSeconds = Integer.parseInt(ConfigProperties.getProperty(ConfigProperties.KEY_IMPLICITLY_WAIT_TIME_SEC));

    protected AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected boolean isElementPresent(WebElement element) {
        new WebDriverWait(driver, timeOutInSeconds).until(webDriver -> element.isDisplayed());
        return element.isDisplayed();
    }

    protected boolean isElementNotPresent(WebElement element) {
        new WebDriverWait(driver, timeOutInSeconds).until(webDriver -> (!element.isDisplayed()));
        return !element.isDisplayed();
    }
}
