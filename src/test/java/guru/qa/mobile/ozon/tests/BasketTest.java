package guru.qa.mobile.ozon.tests;

import guru.qa.mobile.ozon.pages.MainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

@Feature("Ozon")
@Story("Корзина")
@Tags({@Tag("Basket"), @Tag("Mobile"), @Tag("Ozon")})
public class BasketTest extends BaseMobileTest{

    @Test
    @DisplayName("Проверка страницы 'Корзина'")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void basketPage() {
        new MainPage()
                .openBasketPage();
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void addItem() {
        new MainPage()
                .search("iphone 12")
                .addFirstItemToBasket()
                .openBasketPage()
                .checkItemExist();
    }
}
