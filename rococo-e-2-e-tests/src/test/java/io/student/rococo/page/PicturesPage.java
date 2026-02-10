package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PicturesPage {
    private final SelenideElement addPictureButton = $(byText("Добавить картину"));
    private final SelenideElement searchInput = $("button[type='search']");
    private final SelenideElement searchButton = $(".btn-icon.variant-soft-surface.ml-4");

    public PicturesPage checkAddPictureButtonDisplayed() {
        addPictureButton.should(appear);
        return this;
    }
}
