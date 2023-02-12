package guru.qa.web.swaglabs.test;

import guru.qa.web.swaglabs.pages.MainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import java.util.Collections;

import static guru.qa.web.swaglabs.data.Items.backpack;
import static guru.qa.web.swaglabs.data.Items.bike_light;
import static guru.qa.web.swaglabs.data.Users.standart_user;
import static guru.qa.web.swaglabs.pages.LoginPage.openLoginPage;
import static io.qameta.allure.SeverityLevel.BLOCKER;

@Feature("SwagLabs")
@Story("Корзина")
@Tags({@Tag("Basket")})
public class BasketTest extends BaseTest {

    @BeforeEach
    public void auth() {
        openLoginPage()
                .successAuth(standart_user);
    }

    @Test
    @DisplayName("Добавление товара в корзину из карточки товара и удаление из корзины")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void test01() {
        new MainPage()
                .openItemPage(backpack)
                .checkItemName(backpack)
                .addItemToBasket()
                .openBasket()
                .checkItemsAtBasket(Collections.singletonList(backpack))
                .removeItemFromBasket(backpack)
                .checkThatItemIsAbsent(backpack);
    }

    @Test
    @DisplayName("Добавление товара в корзину с главной страницы и удаление")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void test02() {
        new MainPage()
                .addItemToBasket(bike_light)
                .openBasket()
                .checkItemsAtBasket(Collections.singletonList(bike_light))
                .openSidebarMenu()
                .openMainPage()
                .removeItemFromBasket(bike_light)
                .openBasket()
                .checkThatItemIsAbsent(bike_light);
    }
}
