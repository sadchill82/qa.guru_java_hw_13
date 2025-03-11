package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import helpers.ConfigManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    @BeforeAll
    static void configure() {
        Configuration.browserSize = ConfigManager.getScreenResolution();
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.remote = ConfigManager.getSelenoidUrl();
        Configuration.browser = ConfigManager.getBrowser();
        Configuration.browserVersion = ConfigManager.getBrowserVersion();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", ConfigManager.isEnableVNC(),
                "enableVideo", ConfigManager.isEnableVideo()
        ));
        Configuration.browserCapabilities = capabilities;

    }

    @AfterEach
    void closeWebDriver() {
        Selenide.closeWebDriver();
    }

    @AfterEach
    void addAttachment() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
    }

}