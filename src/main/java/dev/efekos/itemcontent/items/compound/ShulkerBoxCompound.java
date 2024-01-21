package dev.efekos.itemcontent.items.compound;

import dev.efekos.itemcontent.items.ItemContent;

import java.util.List;

/**
 * Represents the items inside a shulker box.
 */
public class ShulkerBoxCompound {
    private final List<ItemContent> Items;

    public ShulkerBoxCompound(List<ItemContent> items) {
        Items = items;
    }

    public List<ItemContent> getItems() {
        return Items;
    }
}
