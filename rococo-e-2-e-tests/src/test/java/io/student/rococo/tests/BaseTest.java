package io.student.rococo.tests;

import com.codeborne.selenide.Configuration;
import io.student.rococo.config.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        open(Config.getInstance().frontUrl());
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
