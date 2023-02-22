package guru.qa.mobile.ozon.pages;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.appium.SelenideAppium.back;
import static guru.qa.utils.MobileHelper.checkPopUp;
import static io.appium.java_client.AppiumBy.id;

public class MainPage implements ISections, ISearch {

    public MainPage() {
//        $(id("ru.ozon.app.android:id/design_bottom_sheet")).shouldBe(visible);
//        back();
        checkPopUp();
        $(id(mainMenuButton)).shouldHave(attribute("selected", "true"));
    }
}
