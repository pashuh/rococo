package io.student.rococo.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement passwordSubmitInput = $("#passwordSubmit");
    private final SelenideElement registerButton = $("button[type='submit']");
    private final SelenideElement welcomeRococo = $(".form__subheader");
    private final SelenideElement errorMessage = $(".form__error");

    public static final String ERROR_MESSAGE_USER_EXISTS = "Username '%s' already exists";


    public RegisterPage setUserName(String username) {
        usernameInput.setValue(username);
        return this;
    }

    public RegisterPage setPasswordInput(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public RegisterPage setPasswordSubmit(String password) {
        passwordSubmitInput.setValue(password);
        return this;
    }

    public RegisterPage submitRegisterButton() {
        registerButton.click();
        return this;
    }

    public void checkMessageWelcomeRococo() {
        welcomeRococo
                .shouldBe(Condition.visible)
                .shouldHave(text("Добро пожаловать в Ro"));
        welcomeRococo
                .shouldHave(text("coco"));
    }

    public void checkErrorMessageUserExists(String userName) {
        checkErrorMessage(String.format("Username `%s` already exists", userName));
    }

    public void checkErrorMessagePasswordNotEqual() {
        checkErrorMessage("Passwords should be equal");
    }

    public void checkErrorMessage(String messageValue) {
        errorMessage
                .shouldBe(Condition.visible)
                .shouldHave(text(messageValue));
    }
}
