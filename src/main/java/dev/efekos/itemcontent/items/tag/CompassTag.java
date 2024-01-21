package dev.efekos.itemcontent.items.tag;

import dev.efekos.itemcontent.items.compound.CompassPositionCompound;

public class CompassTag extends ItemTag{
    private final CompassPositionCompound LodestonePos;
    private final String LodestoneDimension;
    private final boolean LodestoneTracked;

    public CompassTag(ItemTag oldItemTag, CompassPositionCompound lodestonePos, String lodestoneDimension, boolean lodestoneTracked) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        LodestonePos = lodestonePos;
        LodestoneDimension = lodestoneDimension;
        LodestoneTracked = lodestoneTracked;
    }

    public CompassPositionCompound getLodestonePos() {
        return LodestonePos;
    }

    public String getLodestoneDimension() {
        return LodestoneDimension;
    }

    public boolean isLodestoneTracked() {
        return LodestoneTracked;
    }
}
