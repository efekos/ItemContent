package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.compound.TrimCompound;

public class TrimTag extends ItemTag{
    private final TrimCompound Trim;

    public TrimTag(ItemTag oldItemTag, TrimCompound trim){
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.Trim = trim;
    }

    public TrimCompound getTrim() {
        return Trim;
    }
}
