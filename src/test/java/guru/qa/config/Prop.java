package guru.qa.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

public class Prop {

    private static Class<? extends PropInterface> getPropertySource() {
        String env = System.getProperty("env");
        if (env == null || env.equals("null")) {
            return PropInterfaceTest.class;
        } else if (env.equals("test")) {
            return PropInterfaceTest.class;
        } else {
            throw new RuntimeException("Invalid value for system property 'env'! Expected one of:[null, 'test']");
        }
    }

    public static final PropInterface PROP = ConfigFactory.create(getPropertySource());

    @LoadPolicy(LoadType.MERGE)
    @Sources({"system:properties", "classpath:test.properties"})
    interface PropInterfaceTest extends PropInterface {
    }

    public interface PropInterface extends Config {

        // Web tests properties
        @Key("webBrowserName")
        String getBrowserName();

        @Key("webBrowserVersion")
        String getBrowserVersion();

        @Key("webBaseUrl")
        String getBaseUrl();

        @Key("webBrowserSize")
        String getBrowserSize();

        @Key("webIsRemote")
        Boolean isRemote();

        @Key("webRemoteUrl")
        String getRemoteUrl();

        @Key("webPageLoadTimeout")
        Long getPageLoadTimeout();

        @Key("webTimeout")
        Long getTimeout();

        @Key("webIsHeadless")
        Boolean isHeadless();

        // Api tests properties
        @Key("dummyToken")
        String getDummyToken();

        @Key("dummyBaseUrl")
        String getDummyBaseUrl();

        // Mobile tests properties
        @Key("browserstack.user")
        String getBrowserStackUser();

        @Key("browserstack.password")
        String getBrowserStackPassword();

        @Key("androidVersion")
        String getAndroidversion();

        @Key("androidDevice")
        String getAndroidDevice();

        @Key("androidApp")
        String getAndroidApp();

        @Key("buildName")
        String getBuildName();

        @Key("mobilePlatform")
        String getMobilePlatform();

        @Key("local.url")
        String getLocalUrl();

        @Key("android.local.version")
        String getLocalAndroidVersion();

        @Key("android.local.device")
        String getLocalAndroidDevice();

        @Key("android.local.app.path")
        String getLocalAndroidAppPath();

        @Key("appPackage")
        String getLocalAndroidAppPackage();

        @Key("appActivity")
        String getLocalAndroidAppActivity();
    }
}
