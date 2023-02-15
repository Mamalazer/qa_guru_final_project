package guru.qa.web.swaglabs.test;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static guru.qa.web.swaglabs.data.Users.locked_user;
import static guru.qa.web.swaglabs.data.Users.standart_user;
import static guru.qa.web.swaglabs.pages.LoginPage.openLoginPage;
import static io.qameta.allure.SeverityLevel.BLOCKER;

@Feature("SwagLabs")
@Story("Авторизация")
@Tags({@Tag("Auth"), @Tag("Web"), @Tag("SwagLabs")})
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успешный логин и Logout")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void test01() {
        openLoginPage()
                .successAuth(standart_user)
                .openSidebarMenu()
                .logOut();
    }

    @Test
    @DisplayName("Неуспешный логин")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void test02() {
        openLoginPage()
                .failedAuth(locked_user);
    }
}
