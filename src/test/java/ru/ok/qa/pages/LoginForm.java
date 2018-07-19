package ru.ok.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends AbstractPage {

    @FindBy(id = "field_email")
    private WebElement loginField;

    @FindBy(id = "field_password")
    private WebElement passwordField;

    @FindBy(css = "input[data-l='t,sign_in']")
    private WebElement loginButton;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public void setLogin(String login) {
        loginField.clear();
        loginField.sendKeys(login);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
