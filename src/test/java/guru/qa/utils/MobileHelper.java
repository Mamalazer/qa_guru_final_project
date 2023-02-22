package guru.qa.utils;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.appium.SelenideAppium.back;
import static io.appium.java_client.AppiumBy.id;

public class MobileHelper {

    public static void checkPopUp() {
        if ($(id("ru.ozon.app.android:id/design_bottom_sheet")).is(exist)) {
            back();
        }
    }
}
