package io.student.rococo.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameInput = $("[name='username']");
    private final SelenideElement passwordInput = $("[name='password']");
    private final SelenideElement loginButton = $("button[type='submit']");
    private final SelenideElement registerButton = $(".form__link");
    private final SelenideElement loginError = $(".login__error");


    public MainPage loginSuccess(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
        return new MainPage();
    }
    public LoginPage loginNotSuccess(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
        return new LoginPage();
    }
    public RegisterPage submitRegisterButton() {
        registerButton.click();
        return new RegisterPage();
    }
    public void checkMessageLoginError() {
        loginError
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Неверные учетные данные пользователя"));
    }
    public LoginPage loginPageShouldBeDisplayed() {
        usernameInput.should(appear);
        passwordInput.should(appear);
        loginButton.should(appear);
        registerButton.should(appear);
        loginError.should(appear);
        return this;
    }
}
