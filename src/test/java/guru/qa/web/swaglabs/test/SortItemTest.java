package guru.qa.web.swaglabs.test;

import guru.qa.web.swaglabs.pages.MainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static guru.qa.web.swaglabs.data.Users.standart_user;
import static guru.qa.web.swaglabs.pages.LoginPage.openLoginPage;
import static guru.qa.web.swaglabs.pages.MainPage.SortOption.*;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Feature("SwagLabs")
@Story("Сортировка товаров")
@Tags({@Tag("Sort")})
public class SortItemTest extends BaseTest{

    @BeforeEach
    public void auth() {
        openLoginPage()
                .successAuth(standart_user);
    }

    @Test
    @DisplayName("Сортировка товаров в алфавитном порядке")
    @Owner("d.kuznetsov")
    @Severity(NORMAL)
    public void test01() {
        new MainPage()
                .setSortOption(ALPHABETICAL)
                .checkAlphabeticalSort();
    }

    @Test
    @DisplayName("Сортировка товаров в обратном алфавитном порядке")
    @Owner("d.kuznetsov")
    @Severity(NORMAL)
    public void test02() {
        new MainPage()
                .setSortOption(REVERSE_ALPHABETICAL)
                .checkReverseAlphabeticalSort();
    }

    @Test
    @DisplayName("Сортировка товаров по цене(от меньшего к большему)")
    @Owner("d.kuznetsov")
    @Severity(NORMAL)
    public void test03() {
        new MainPage()
                .setSortOption(LOW_TO_HIGH_PRICE)
                .checkPriceSort();
    }

    @Test
    @DisplayName("Сортировка товаров по цене(от большему к меньшему)")
    @Owner("d.kuznetsov")
    @Severity(NORMAL)
    public void test04() {
        new MainPage()
                .setSortOption(HIGH_TO_LOW_PRICE)
                .checkReversePriceSort();
    }
}
