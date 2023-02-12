package guru.qa.web.swaglabs.pages;

import guru.qa.web.swaglabs.data.Item;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ItemPage implements ISidebarLink, IBasketLink {

    public ItemPage() {
        $("#inventory_item_container").shouldBe(visible);
    }

    @Step("Проверить имя товара")
    public ItemPage checkItemName(Item item) {
        $(".inventory_details_name").shouldHave(text(item.getItemName()));
        return this;
    }

    @Step("Добавить товар в корзину")
    public ItemPage addItemToBasket() {
        $(byText("Add to cart")).click();
        return this;
    }
}
