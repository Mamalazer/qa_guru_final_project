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

        @Key("web.browserName")
        String getBrowserName();

        @Key("web.browserVersion")
        String getBrowserVersion();

        @Key("web.baseUrl")
        String getBaseUrl();

        @Key("web.browserSize")
        String getBrowserSize();

        @Key("web.isRemote")
        Boolean isRemote();

        @Key("web.remoteUrl")
        String getRemoteUrl();

        @Key("web.pageLoadTimeout")
        Long getPageLoadTimeout();

        @Key("web.timeout")
        Long getTimeout();

        @Key("web.headless")
        Boolean isHeadless();
    }
}
