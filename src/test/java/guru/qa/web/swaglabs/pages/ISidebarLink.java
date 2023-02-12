package guru.qa.web.swaglabs.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public interface ISidebarLink {

    @Step("Открыть сайдбар с меню")
    default SidebarMenuPage openSidebarMenu() {
        $("#react-burger-menu-btn").click();
        return new SidebarMenuPage();
    }
}
