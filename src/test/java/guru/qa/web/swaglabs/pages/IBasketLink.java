package guru.qa.web.swaglabs.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public interface IBasketLink {

    @Step("Открыть корзину")
    default BasketPage openBasket() {
        $(".shopping_cart_link").click();
        return new BasketPage();
    }
}
