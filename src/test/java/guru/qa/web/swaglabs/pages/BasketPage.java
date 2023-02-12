package guru.qa.web.swaglabs.pages;

import guru.qa.web.swaglabs.data.Item;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasketPage implements ISidebarLink {

    private static final String itemsList = ".cart_item";
    private static final String itemsListNames = itemsList + " .inventory_item_name";

    public BasketPage() {
        $(".header_secondary_container").shouldHave(text("Your Cart"));
    }

    @Step("Убедиться, что в корзине присутствуют товары")
    public BasketPage checkItemsAtBasket(List<Item> items) {
        String[] expectedItemsNames = items.stream()
                .map(Item::getItemName)
                .toArray(String[]::new);
        $$(itemsListNames).shouldHave(textsInAnyOrder(expectedItemsNames));
        return this;
    }

    @Step("Удалить из корзины товар")
    public BasketPage removeItemFromBasket(Item item) {
        $(byText(item.getItemName()))
                .ancestor(itemsList)
                .$(byText("Remove"))
                .click();
        return this;
    }

    @Step("Убедиться, что в корзине отсутствует товар")
    public BasketPage checkThatItemIsAbsent(Item item) {
        $$(byText(item.getItemName())).shouldHave(size(0));
        return this;
    }

    @Step("Перейти к чекауту")
    public CheckoutPage goToCheckout() {
        $("#checkout").click();
        return new CheckoutPage();
    }
}
