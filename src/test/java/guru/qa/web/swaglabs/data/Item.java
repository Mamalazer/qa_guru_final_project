package guru.qa.web.swaglabs.data;

import lombok.Getter;

@Getter
public class Item {

    private String itemName;
    private String itemPrice;

    public Item(String itemName, String itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
