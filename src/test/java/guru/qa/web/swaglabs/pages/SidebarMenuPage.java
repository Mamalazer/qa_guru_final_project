package guru.qa.web.swaglabs.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SidebarMenuPage {

    private final String allItemsLink = "#inventory_sidebar_link";
    private final String aboutLink = "#about_sidebar_link";
    private final String logOutLink = "#logout_sidebar_link";
    private final String resetAppStateLink = "#reset_sidebar_link";

    public SidebarMenuPage() {
        $(allItemsLink).shouldBe(visible);
        $(aboutLink).shouldBe(visible);
        $(logOutLink).shouldBe(visible);
        $(resetAppStateLink).shouldBe(visible);
    }

    @Step("Разлогинться")
    public LoginPage logOut() {
        $(logOutLink).click();
        return new LoginPage();
    }

    @Step("Перейти к главной странице")
    public MainPage openMainPage() {
        $(allItemsLink).click();
        return new MainPage();
    }
}
