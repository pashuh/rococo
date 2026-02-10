package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ArtistsPage {
    private final SelenideElement addArtistsButton = $(byText("Добавить художника"));
    private final SelenideElement searchInput = $("button[type='search']");
    private final SelenideElement searchButton = $(".btn-icon.variant-soft-surface.ml-4");

    public ArtistsPage checkAddArtistButtonDisplayed() {
        addArtistsButton.should(appear);
        return this;
    }

}
