package guru.qa.web.swaglabs.test;

import guru.qa.web.swaglabs.pages.MainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static guru.qa.web.swaglabs.data.Items.fleece_jacket;
import static guru.qa.web.swaglabs.data.Users.standart_user;
import static guru.qa.web.swaglabs.pages.LoginPage.openLoginPage;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static java.util.Collections.singletonList;

@Feature("SwagLabs")
@Story("Оплата заказа")
@Tags({@Tag("Payment"), @Tag("Smoke")})
public class PaymentTest extends BaseTest {

    @BeforeEach
    public void auth() {
        openLoginPage()
                .successAuth(standart_user);
    }

    @Test
    @DisplayName("Оплата заказа")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void test01() {
        new MainPage()
                .addItemToBasket(fleece_jacket)
                .openBasket()
                .checkItemsAtBasket(singletonList(fleece_jacket))
                .goToCheckout()
                .fillUserinfo(standart_user)
                .submitUserInfo()
                .checkItemsAtCheckout(singletonList(fleece_jacket))
                .checkTotalSum("53.99")
                .submitPurchase();
    }
}
