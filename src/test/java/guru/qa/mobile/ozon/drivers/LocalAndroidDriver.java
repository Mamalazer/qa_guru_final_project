package guru.qa.mobile.ozon.drivers;

import com.codeborne.selenide.WebDriverProvider;
import guru.qa.utils.ApkInfoHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static guru.qa.config.Prop.PROP;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;

public class LocalAndroidDriver implements WebDriverProvider {

    private void initPackageAndActivity() {
        ApkInfoHelper helper = new ApkInfoHelper();
        if (PROP.getLocalAndroidAppPackage() == null || PROP.getLocalAndroidAppPackage().isEmpty()) {
            System.setProperty("appPackage", helper.getAppPackageFromApk());
        }
        if (PROP.getLocalAndroidAppPackage() == null || PROP.getLocalAndroidAppPackage().isEmpty()) {
            System.setProperty("appActivity", helper.getAppMainActivity());
        }
    }

    @Override
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        initPackageAndActivity();
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setPlatformVersion(PROP.getLocalAndroidVersion());
        options.setDeviceName(PROP.getLocalAndroidDevice());
        options.setNewCommandTimeout(Duration.ofSeconds(11));
        options.setFullReset(false);
        options.setApp(new File(PROP.getLocalAndroidAppPath()).getAbsolutePath());
        options.setAppPackage(PROP.getLocalAndroidAppPackage());
        options.setAppActivity(PROP.getLocalAndroidAppActivity());

        try {
            return new AndroidDriver(new URL(PROP.getLocalUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
