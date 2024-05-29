package dev.efekos.itemcontent.items.tag;

public class AxolotlBucketTag extends ItemTag {
    private final Integer Variant;

    public AxolotlBucketTag(ItemTag oldItemTag, Integer variant) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.Variant = variant;
    }

    public Integer getVariant() {
        return Variant;
    }
}
