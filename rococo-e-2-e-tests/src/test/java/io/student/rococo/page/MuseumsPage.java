package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MuseumsPage {
    private final SelenideElement addMuseumButton = $(byText("Добавить музей"));
    private final SelenideElement searchInput = $("button[type='search']");
    private final SelenideElement searchButton = $(".btn-icon.variant-soft-surface.ml-4");

    public MuseumsPage checkAddMuseumButtonDisplayed() {
        addMuseumButton.should(appear);
        return this;
    }
}








