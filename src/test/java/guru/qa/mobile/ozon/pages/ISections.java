package guru.qa.mobile.ozon.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public interface ISections {

    String mainMenuButton = "ru.ozon.app.android:id/menu_main";
    String catalogButton = "ru.ozon.app.android:id/menu_catalog";
    String discountButton = "ru.ozon.app.android:id/menu_eleven_november";
    String basketButton = "ru.ozon.app.android:id/menu_cart";

    @Step("Открыть раздел 'Главная'")
    default MainPage openMainPage() {
        $(id(mainMenuButton)).click();
        return new MainPage();
    }

    @Step("Открыть раздел 'Каталог'")
    default CatalogPage openCatalogPage() {
        $(id(catalogButton)).click();
        return new CatalogPage();
    }

    @Step("Открыть раздел 'Скидки'")
    default DiscountPage openDiscountPage() {
        $(id(discountButton)).click();
        return new DiscountPage();
    }

    @Step("Открыть раздел 'Корзина'")
    default BasketPage openBasketPage() {
        $(id(basketButton)).click();
        return new BasketPage();
    }
}
