package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.compound.SuspiciousStewEffectCompound;

public class SuspiciousStewTag extends ItemTag{
    private final SuspiciousStewEffectCompound[] effects;

    public SuspiciousStewTag(ItemTag oldItemTag, SuspiciousStewEffectCompound[] potionEffects){
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.effects = potionEffects;
    }

    public SuspiciousStewEffectCompound[] getEffects() {
        return effects;
    }
}
