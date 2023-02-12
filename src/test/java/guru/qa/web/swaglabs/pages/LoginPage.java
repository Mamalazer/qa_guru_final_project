package guru.qa.web.swaglabs.pages;

import com.codeborne.selenide.Selenide;
import guru.qa.web.swaglabs.data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static final String loginField = "#user-name";
    private static final String passwordField = "#password";
    private static final String loginButton = "#login-button";

    public LoginPage() {
        $(".login_logo").shouldBe(visible);
    }

    @Step("Открыть страницу авторизации")
    public static LoginPage openLoginPage() {
        Selenide.open("/");
        return new LoginPage();
    }

    @Step("Успешная авторизация")
    public MainPage successAuth(User user) {
        $(loginField).setValue(user.getLogin());
        $(passwordField).setValue(user.getPassword());
        $(loginButton).click();
        return new MainPage();
    }

    @Step("Неуспешная авторизация")
    public void failedAuth(User user) {
        $(loginField).setValue(user.getLogin());
        $(passwordField).setValue(user.getPassword());
        $(loginButton).click();
        $("[data-test='error']")
                .shouldHave(text("Epic sadface: Username and password do not match any user in this service"));
    }
}
