package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.ItemContent;

import java.util.List;

public class CrossbowTag extends ItemTag {
    private final List<ItemContent> ChargedProjectiles;
    private final boolean Charged;

    public CrossbowTag(ItemTag oldItemTag, List<ItemContent> contents, boolean charged) {
        super(oldItemTag.getDamage(), oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.ChargedProjectiles = contents;
        this.Charged = charged;
    }

    public List<ItemContent> getChargedProjectiles() {
        return ChargedProjectiles;
    }

    public boolean isCharged() {
        return Charged;
    }
}
