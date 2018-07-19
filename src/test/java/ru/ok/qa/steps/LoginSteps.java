package ru.ok.qa.steps;

import ru.ok.qa.pages.LoginForm;

public class LoginSteps {

    private LoginForm loginForm;

    public LoginSteps(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public void fillLoginForm(String login, String password) {
        loginForm.setLogin(login);
        loginForm.setPassword(password);
    }

    public void submitLogin() {
        loginForm.clickLoginButton();
    }
}
