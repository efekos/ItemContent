package dev.efekos.itemcontent.items.tag;

public class SkullTag extends ItemTag {
    private final String SkullOwner;

    public SkullTag(ItemTag oldItemTag, String owner) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.SkullOwner = owner;
    }

    public String getSkullOwner() {
        return SkullOwner;
    }
}
