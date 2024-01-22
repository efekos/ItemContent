package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.compound.SuspiciousStewEffectCompound;

public class SuspiciousStewTag extends ItemTag{
    private final SuspiciousStewEffectCompound[] Effects;

    public SuspiciousStewTag(ItemTag oldItemTag, SuspiciousStewEffectCompound[] potionEffects){
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.Effects = potionEffects;
    }

    public SuspiciousStewEffectCompound[] getEffects() {
        return Effects;
    }
}
