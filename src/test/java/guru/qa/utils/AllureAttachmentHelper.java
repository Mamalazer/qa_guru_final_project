package guru.qa.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import guru.qa.utils.browserstack.models.BrowserStackSessionInfo;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.sessionId;
import static guru.qa.config.Prop.PROP;
import static guru.qa.utils.browserstack.BrowserStackHelper.getBrowserStackLog;
import static org.openqa.selenium.logging.LogType.BROWSER;

@Slf4j(topic = "AllureAttachmentHelper")
public class AllureAttachmentHelper {

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return Selenide.screenshot(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return WebDriverRunner.source()
                .getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "{fileName}", type = "text/html")
    public static byte[] attachHtml(String fileName) {
        return WebDriverRunner.source()
                .getBytes(StandardCharsets.UTF_8);
    }

    public static void browserConsoleLogs() {
        attachAsText(
                "Browser console logs",
                String.join("\n", Selenide.getWebDriverLogs(BROWSER))
        );
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl()
                + "' type='video/mp4'></video></body></html>";
    }

    public static URL getVideoUrl() {
        String videoUrl = PROP.getRemoteUrl() + "/video/" + sessionId() + ".mp4";

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String getBrowserStackVideo(BrowserStackSessionInfo sessionInfo) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + sessionInfo.getAutomationSession().getVideoUrl()
                + "' type='video/mp4'></video></body></html>";
    }

    @Attachment(value = "Logs", type = "text/plain")
    public static byte[] logs(BrowserStackSessionInfo sessionInfo) {
        return getBrowserStackLog(sessionInfo.getAutomationSession().getLogs())
                .getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Private link to the test run", type = "text/html", fileExtension = ".html")
    public static String privateLink(BrowserStackSessionInfo sessionInfo) {
        String browserUrl = sessionInfo.getAutomationSession().getBrowserUrl();
        return "<html><body><a href='" + browserUrl + "' target='_blank'>Private link to the test run</a></body></html>";
    }

    @Attachment(value = "Public link to the test run", type = "text/html", fileExtension = ".html")
    public static String publicLink(BrowserStackSessionInfo sessionInfo) {
        String publicUrl = sessionInfo.getAutomationSession().getPublicUrl();
        return "<html><body><a href='" + publicUrl + "' target='_blank'>Public link to the test run</a></body></html>";
    }
}
