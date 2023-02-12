package guru.qa.web.swaglabs.pages;

import guru.qa.web.swaglabs.data.Item;
import guru.qa.web.swaglabs.data.User;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutPage implements ISidebarLink, IBasketLink {

    private static final String itemsList = ".cart_item";
    private static final String itemsListNames = itemsList + " .inventory_item_name";

    public CheckoutPage() {
        $(".header_secondary_container").shouldHave(text("Checkout: Your Information"));
    }

    @Step("Заполнить пользовательские данные")
    public CheckoutPage fillUserinfo(User user) {
        $("#first-name").setValue(user.getFirstName());
        $("#last-name").setValue(user.getLastName());
        $("#postal-code").setValue(user.getZipCode());
        return this;
    }

    @Step("Подтвердить ввод пользовательских данных")
    public CheckoutPage submitUserInfo() {
        $("#continue").click();
        $(".header_secondary_container").shouldHave(text("Checkout: Overview"));
        return this;
    }

    @Step("Убедиться, что на чекауте присутствуют товары")
    public CheckoutPage checkItemsAtCheckout(List<Item> items) {
        String[] expectedItemsNames = items.stream()
                .map(Item::getItemName)
                .toArray(String[]::new);
        $$(itemsListNames).shouldHave(textsInAnyOrder(expectedItemsNames));
        return this;
    }

    @Step("Убедиться, что финальная сумма равна {sum}")
    public CheckoutPage checkTotalSum(String sum) {
        $(".summary_total_label").shouldHave(text(sum));
        return this;
    }

    @Step("Подтвердить оплату заказа")
    public CheckoutPage submitPurchase() {
        $("#finish").click();
        $("#checkout_complete_container").shouldHave(text("THANK YOU FOR YOUR ORDER"));
        return this;
    }
}
