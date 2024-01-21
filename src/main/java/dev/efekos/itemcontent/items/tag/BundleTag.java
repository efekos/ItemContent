package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.ItemContent;

import java.util.List;

public class BundleTag extends ItemTag{
    private final List<ItemContent> Items;

    public BundleTag(ItemTag oldItemTag, List<ItemContent>contents) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.Items = contents;
    }

    public List<ItemContent> getItems() {
        return Items;
    }
}
