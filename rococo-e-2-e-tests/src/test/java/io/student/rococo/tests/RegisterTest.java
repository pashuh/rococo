package io.student.rococo.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.student.rococo.page.MainPage;

import static com.codeborne.selenide.Selenide.page;

public class RegisterTest extends BaseTest {


    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void shouldRegisterNewUser() {
        page(MainPage.class)
                .clickLoginButton()
                .submitRegisterButton()
                .setUserName("test98")
                .setPasswordInput("009988")
                .setPasswordSubmit("009988")
                .submitRegisterButton()
                .checkMessageWelcomeRococo();
    }

    @Test
    @DisplayName("Ошибка при регистрации - пользователь уже существует")
    public void shouldNotRegisterUserWithExistingUsername() {
        String userName = "test";
        page(MainPage.class)
                .clickLoginButton()
                .submitRegisterButton()
                .setUserName(userName)
                .setPasswordInput("7777")
                .setPasswordSubmit("7777")
                .submitRegisterButton()
                .checkErrorMessageUserExists(userName);
    }

    @Test
    @DisplayName("Ошибка при авторизации - пароли не совпадают")
    public void shouldShowErrorIfPasswordAndSubmitPasswordAreNotEqual() {
        page(MainPage.class)
                .clickLoginButton()
                .submitRegisterButton()
                .setUserName("sdfsdf")
                .setPasswordInput("7777")
                .setPasswordSubmit("7788")
                .submitRegisterButton()
                .checkErrorMessagePasswordNotEqual();
    }
}
