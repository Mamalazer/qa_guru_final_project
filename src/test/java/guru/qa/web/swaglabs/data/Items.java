package guru.qa.web.swaglabs.data;

import java.util.Arrays;
import java.util.List;

public class Items {

    public static final Item backpack = new Item("Sauce Labs Backpack", "29.99");
    public static final Item bike_light = new Item("Sauce Labs Bike Light", "9.99");
    public static final Item bolt_t_shirt = new Item("Sauce Labs Bolt T-Shirt", "15.99");
    public static final Item fleece_jacket = new Item("Sauce Labs Fleece Jacket", "49.99");
    public static final Item onesie = new Item("Sauce Labs Onesie", "7.99");
    public static final Item test_all_things_t_shirt = new Item("Test.allTheThings() T-Shirt (Red)", "15.99");

    public static List<Item> getAllItems() {
        return Arrays.asList(
                backpack, bike_light, bolt_t_shirt, fleece_jacket, onesie, test_all_things_t_shirt);
    }
}
