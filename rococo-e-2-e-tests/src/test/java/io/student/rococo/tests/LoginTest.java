package io.student.rococo.tests;

import io.student.rococo.jupiter.annotation.User;
import io.student.rococo.model.UserJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.student.rococo.page.MainPage;

import static com.codeborne.selenide.Selenide.page;

public class LoginTest extends BaseTest {

    @Test
    @User
    @DisplayName("Главная страница отображается после авторизации")
    public void mainPageShouldBeDisplayedAfterSuccessLogin(UserJson userJson) {
        page(MainPage.class)
                .clickLoginButton()
                .loginSuccess(userJson.username()
                        ,userJson.password())
                .mainPageShouldBeDisplayed();
    }

    @Test
    @DisplayName("Пользователь остается на странице авторизации при неверных данных пользователя")
    public void userShouldStayOnLoginPageAfterLoginWithBadCredentials() {page(MainPage.class)
            .clickLoginButton()
            .loginNotSuccess("test44", "4455")
            .loginPageShouldBeDisplayed();
    }

    @Test
    @DisplayName("Ошибка при авторизации - неверные данные пользователя")
    public void failedAuthorizationIncorrectPassword() {page(MainPage.class)
            .clickLoginButton()
            .loginNotSuccess("test777", "123")
            .checkMessageLoginError();
    }

    @Test
    @DisplayName("На главной странице отображается кнопка 'Картины' после авторизации")
    public void mainPagePicturesButtonShouldBeDisplayedAfterSuccessfulLogin() {page(MainPage.class)
            .clickLoginButton()
            .loginSuccess("test44", "4444")
            .clickPictureButton()
            .checkAddPictureButtonDisplayed();
    }

    @Test
    @DisplayName("На главной странице отображается кнопка 'Картины' после авторизации")
    public void mainPageArtistsButtonShouldBeDisplayedAfterSuccessfulLogin() {page(MainPage.class)
            .clickLoginButton()
            .loginSuccess("test44", "4444")
            .clickArtistsButton()
            .checkAddArtistButtonDisplayed();
    }

    @Test
    @DisplayName("На главной странице отображается кнопка 'Картины' после авторизации")
    public void mainPageMuseumsButtonShouldBeDisplayedAfterSuccessfulLogin() {page(MainPage.class)
            .clickLoginButton()
            .loginSuccess("test44", "4444")
            .clickMuseumsButton()
            .checkAddMuseumButtonDisplayed();
    }
}
