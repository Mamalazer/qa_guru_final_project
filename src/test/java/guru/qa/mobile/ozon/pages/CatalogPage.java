package guru.qa.mobile.ozon.pages;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class CatalogPage implements ISections, ISearch {

    public CatalogPage() {
        $(id(catalogButton)).shouldHave(attribute("selected", "true"));
        $(accessibilityId("tab_1")).shouldHave(text("Каталог"));
        $(accessibilityId("tab_2")).shouldHave(text("Весь Ozon"));
    }

    @Step("Проверить наличие категорий")
    public CatalogPage checkCategories() {
        $$(id("ru.ozon.app.android:id/tvTitle")).shouldHave(CollectionCondition.textsInAnyOrder(
                "Одежда и обувь", "Электроника", "Дом и сад", "Детские товары", "Красота и здоровье",
                "Детская одежда и обувь", "Бытовая техника", "Книги", "Для меня")
        );
        return this;
    }
}
