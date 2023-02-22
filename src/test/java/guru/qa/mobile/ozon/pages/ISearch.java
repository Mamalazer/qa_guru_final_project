package guru.qa.mobile.ozon.pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.appium.SelenideAppium.back;
import static io.appium.java_client.AppiumBy.id;

public interface ISearch {

    @Step("Найти товар {itemName}")
    default ItemsPage search(String itemName) {
        $(id("ru.ozon.app.android:id/searchTv")).click();
        $(id("ru.ozon.app.android:id/etSearch")).setValue(itemName);
        back();
        $(AppiumBy.accessibilityId(itemName)).click();
        return new ItemsPage();
    }
}
