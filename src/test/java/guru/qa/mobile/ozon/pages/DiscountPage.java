package guru.qa.mobile.ozon.pages;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class DiscountPage implements ISections, ISearch {

    public DiscountPage() {
        $(id(discountButton)).shouldHave(attribute("selected", "true"));
        $(id("ru.ozon.app.android:id/headerTitleTa")).shouldHave(text("Скидки для вас"));
    }
}
