package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.compound.BannerCompound;

public class BannerTag extends ItemTag {
    private final BannerCompound BlockEntityTag;

    public BannerTag(ItemTag oldItemTag, BannerCompound banner) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.BlockEntityTag = banner;
    }

    public BannerCompound getBlockEntityTag() {
        return BlockEntityTag;
    }
}
