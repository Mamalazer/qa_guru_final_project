package guru.qa.web.swaglabs.pages;

import guru.qa.web.swaglabs.data.Item;
import io.qameta.allure.Step;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static guru.qa.web.swaglabs.data.Items.getAllItems;

public class MainPage implements ISidebarLink, IBasketLink {

    private final String itemsList = ".inventory_item";
    private final String itemsNamesList = itemsList + " .inventory_item_name";
    private final String itemsPricesList = itemsList + " .inventory_item_price";

    @Getter
    public enum SortOption {
        ALPHABETICAL("az"),
        REVERSE_ALPHABETICAL("za"),
        LOW_TO_HIGH_PRICE("lohi"),
        HIGH_TO_LOW_PRICE("hilo");

        private final String optionValue;

        SortOption(String optionValue) {
            this.optionValue = optionValue;
        }
    }

    public MainPage() {
        $(".title").shouldHave(text("Products"));
    }

    @Step("Выбрать сортировку товаров = {option}")
    public MainPage setSortOption(SortOption option) {
        $("[data-test='product_sort_container']").selectOptionByValue(option.getOptionValue());
        return this;
    }

    @Step("Проверить сортировку товаров в алфавитном порядке")
    public MainPage checkAlphabeticalSort() {
        List<String> actualItemsNames = $$(itemsNamesList).texts();
        List<String> expectedSortedItemsNames = getAllItems().stream()
                .map(Item::getItemName)
                .sorted()
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedSortedItemsNames, actualItemsNames);
        return this;
    }

    @Step("Проверить сортировку товаров в обратном алфавитном порядке")
    public MainPage checkReverseAlphabeticalSort() {
        List<String> actualItemsNames = $$(itemsNamesList).texts();
        List<String> expectedSortedItemsNames = getAllItems().stream()
                .map(Item::getItemName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedSortedItemsNames, actualItemsNames);
        return this;
    }

    @Step("Проверить сортировку товаров по цене(от меньшего к большему)")
    public MainPage checkPriceSort() {
        List<String> actualItemsNames = $$(itemsPricesList).texts();
        List<String> expectedSortedItemsNames = getAllItems().stream()
                .map(Item::getItemPrice)
                .map(BigDecimal::new)
                .sorted()
                .map(price -> "$" + price)
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedSortedItemsNames, actualItemsNames);
        return this;
    }

    @Step("Проверить сортировку товаров по цене(от большему к меньшему)")
    public MainPage checkReversePriceSort() {
        List<String> actualItemsNames = $$(itemsPricesList).texts();
        List<String> expectedSortedItemsNames = getAllItems().stream()
                .map(Item::getItemPrice)
                .map(BigDecimal::new)
                .sorted(Comparator.reverseOrder())
                .map(price -> "$" + price)
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedSortedItemsNames, actualItemsNames);
        return this;
    }

    @Step("Открыть карточку товара")
    public ItemPage openItemPage(Item item) {
        $(byText(item.getItemName())).parent().click();
        return new ItemPage();
    }

    @Step("Добавить товар в корзину")
    public MainPage addItemToBasket(Item item) {
        $(byText(item.getItemName()))
                .ancestor(itemsList)
                .$(byText("Add to cart"))
                .click();
        return this;
    }

    @Step("Удалитьтовар из корзины")
    public MainPage removeItemFromBasket(Item item) {
        $(byText(item.getItemName()))
                .ancestor(itemsList)
                .$(byText("Remove"))
                .click();
        return this;
    }
}
