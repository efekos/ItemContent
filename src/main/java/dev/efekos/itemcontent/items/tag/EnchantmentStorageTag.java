package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.compound.EnchantmentCompound;

public class EnchantmentStorageTag extends ItemTag {
    public EnchantmentStorageTag(ItemTag oldItemTag, EnchantmentCompound[] storedEnchantments) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.StoredEnchantments = storedEnchantments;
    }

    private final EnchantmentCompound[] StoredEnchantments;

    public EnchantmentCompound[] getStoredEnchantments() {
        return StoredEnchantments;
    }
}
