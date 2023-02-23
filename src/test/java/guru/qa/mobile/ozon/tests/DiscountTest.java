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

import static io.qameta.allure.SeverityLevel.NORMAL;

@Feature("Ozon")
@Story("Скидки")
@Tags({@Tag("Discount"), @Tag("Mobile"), @Tag("Ozon")})
public class DiscountTest extends BaseMobileTest {

    @Test
    @DisplayName("Проверка страницы 'Скидки'")
    @Owner("d.kuznetsov")
    @Severity(NORMAL)
    public void checkDiscount() {
        new MainPage()
                .changeRegion("Пенза")
                .openDiscountPage();
    }
}
