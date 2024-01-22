package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.compound.PotionEffectCompound;

public class PotionTag extends ItemTag{
    private final PotionEffectCompound[] CustomPotionEffects;
    private final String Potion;
    private final Integer CustomPotionColor;

    public PotionTag(ItemTag oldItemTag, PotionEffectCompound[] potionEffects, Integer customPotionColor) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.CustomPotionEffects = potionEffects;
        this.Potion = null;
        this.CustomPotionColor = customPotionColor;
    }

    public PotionTag(ItemTag oldItemTag, String potion){
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.CustomPotionEffects = null;
        this.Potion = potion;
        this.CustomPotionColor = null;
    }

    public PotionEffectCompound[] getCustomPotionEffects() {
        return CustomPotionEffects;
    }

    public String getPotion() {
        return Potion;
    }

    public Integer getCustomPotionColor() {
        return CustomPotionColor;
    }
}
