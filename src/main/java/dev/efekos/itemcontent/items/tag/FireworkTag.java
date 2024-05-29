package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.compound.FireworkCompound;

public class FireworkTag extends ItemTag {
    private final FireworkCompound Fireworks;

    public FireworkTag(ItemTag oldItemTag, FireworkCompound compound) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.Fireworks = compound;
    }

    public FireworkCompound getFireworks() {
        return Fireworks;
    }
}
