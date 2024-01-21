package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.compound.ShulkerBoxCompound;

public class ShulkerBoxTag extends ItemTag{
    private final ShulkerBoxCompound BlockEntityTag;

    public ShulkerBoxTag(ItemTag oldItemTag, ShulkerBoxCompound compound) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        BlockEntityTag = compound;
    }

    public ShulkerBoxCompound getBlockEntityTag() {
        return BlockEntityTag;
    }
}
