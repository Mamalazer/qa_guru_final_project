package guru.qa.web.swaglabs.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static guru.qa.config.Prop.PROP;
import static guru.qa.utils.AllureAttachmentHelper.*;

public class BaseTest {

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = PROP.getBaseUrl();
        Configuration.browser = PROP.getBrowserName();
        Configuration.browserVersion = PROP.getBrowserVersion();
        Configuration.browserSize = PROP.getBrowserSize();
        Configuration.pageLoadTimeout = PROP.getPageLoadTimeout();
        Configuration.timeout = PROP.getTimeout();
        Configuration.headless = PROP.isHeadless();

        if (PROP.isRemote()) {
            Configuration.remote = PROP.getRemoteUrl() + "/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }
    }

    @AfterEach
    public void tearDown() {
        screenshotAs("Last screenshot");
        pageSource();
        browserConsoleLogs();
        attachHtml("HTML");

        if (PROP.isRemote()) {
            addVideo();
        }

        closeWebDriver();
    }
}
