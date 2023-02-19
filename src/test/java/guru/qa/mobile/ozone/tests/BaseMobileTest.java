package guru.qa.mobile.ozone.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.mobile.ozone.drivers.BrowserStackAndroidDriver;
import guru.qa.mobile.ozone.drivers.LocalAndroidDriver;
import guru.qa.utils.browserstack.models.BrowserStackSessionInfo;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static guru.qa.config.Prop.PROP;
import static guru.qa.utils.AllureAttachmentHelper.*;
import static guru.qa.utils.browserstack.BrowserStackHelper.getSessionInfo;

public class BaseMobileTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 15000;
        Configuration.browserSize = null;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void openBrowser() {
        if (PROP.getMobilePlatform().equals("android")) {
            Configuration.browser = BrowserStackAndroidDriver.class.getName();
        } else {
            Configuration.browser = LocalAndroidDriver.class.getName();
        }
        SelenideAppium.launchApp();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();
        screenshotAs("Last screenshot");
        pageSource();
        closeWebDriver();
        if (!PROP.getMobilePlatform().equals("local")) {
            BrowserStackSessionInfo sessionInfo = getSessionInfo(sessionId);
            getBrowserStackVideo(sessionInfo);
            logs(sessionInfo);
            privateLink(sessionInfo);
            publicLink(sessionInfo);
        }
    }
}
