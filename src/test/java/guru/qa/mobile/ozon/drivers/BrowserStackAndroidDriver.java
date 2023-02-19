package guru.qa.mobile.ozon.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static guru.qa.config.Prop.PROP;

public class BrowserStackAndroidDriver implements WebDriverProvider {

    @Override
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setPlatformName("Android");
        options.setPlatformVersion(PROP.getAndroidversion());
        options.setDeviceName(PROP.getAndroidDevice());
        options.setNewCommandTimeout(Duration.ofSeconds(11));
        options.setFullReset(false);
        options.setApp(PROP.getAndroidApp());
        options.setCapability("build", PROP.getBuildName());

        try {
            return new AndroidDriver(new URL("http://" + PROP.getBrowserStackUser() + ":" +
                    PROP.getBrowserStackPassword() + "@hub-cloud.browserstack.com/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
