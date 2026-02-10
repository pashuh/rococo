package io.student.rococo.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement header = $("header");
    private final SelenideElement headerPicturesButton = header.find(byText("Картины"));
    private final SelenideElement headerArtistsButton = header.find(byText("Художники"));
    private final SelenideElement headerMuseumsButton = header.find(byText("Музеи"));
    private final SelenideElement lightSwitch = header.find("[role='switch']");
    private final SelenideElement loginButton = header.find(byText("Войти"));

    private final SelenideElement main = $("#page-content");;

    private final SelenideElement picturesButton = main.find(byText("Картины"));
    private final SelenideElement artistsButton = main.find(byText("Художники"));
    private final SelenideElement museumsButton = main.find(byText("Музеи"));
    private final SelenideElement userAvatar = $("[data-testid='avatar']");

    public MainPage mainPageShouldBeDisplayed() {
        header.should(appear);
        headerPicturesButton.should(appear);
        headerArtistsButton.should(appear);
        headerMuseumsButton.should(appear);
        picturesButton.should(appear);
        artistsButton.should(appear);
        museumsButton.should(appear);
        lightSwitch.should(appear);
        userAvatar.should(appear);
        return this;
    }

    public MainPage mainPagePicturesButtonShouldBeDisplayed() {
        picturesButton.should(appear);
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage();
    }

    public PicturesPage clickPictureButton() {
        picturesButton.click();
        return new PicturesPage();
    }

    public ArtistsPage clickArtistsButton() {
        artistsButton.click();
        return new ArtistsPage();
    }

    public MuseumsPage clickMuseumsButton() {
        museumsButton.click();
        return new MuseumsPage();
    }
}
