package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.compound.ExplosionCompound;

public class FireworkEffectTag extends ItemTag{
    private final ExplosionCompound Explosion;

    public FireworkEffectTag(ItemTag oldItemTag, ExplosionCompound compound) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        Explosion = compound;
    }

    public ExplosionCompound getExplosion() {
        return Explosion;
    }
}
