package dev.efekos.itemcontent.items.tag;

public class GoatHornTag extends ItemTag{
    private final String instrument;

    public GoatHornTag(ItemTag oldItemTag, String instrument) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.instrument = instrument;
    }

    public String getInstrument() {
        return instrument;
    }
}
