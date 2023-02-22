package guru.qa.mobile.ozon.pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ItemsPage implements ISections, ISearch {

    private final String itemLink = "ru.ozon.app.android:id/rootCl";

    @Step("Добавить товар {itemName} в корзину")
    public ItemsPage addFirstItemToBasket() {
        $(AppiumBy.id("ru.ozon.app.android:id/multiButtonButtonView")).click();
        return this;
    }

    @Step("Убедиться, что в результатах поиска отображаются товары")
    public ItemsPage checkResultsOfSearch() {
        $$(AppiumBy.id(itemLink)).shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Открыть первую карточку товара")
    public ItemPage openFirstItem() {
        $(AppiumBy.id("ru.ozon.app.android:id/contentElementsVAL")).click();
        return new ItemPage();
    }
}
