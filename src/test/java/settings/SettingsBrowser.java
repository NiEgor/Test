package settings;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import helpers.Attach;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class SettingsBrowser {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://globalsqa.com";
        Configuration.timeout = 6000;
    }

    @Step("Добавить вложения")
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    }

