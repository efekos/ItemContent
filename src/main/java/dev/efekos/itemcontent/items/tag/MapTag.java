package dev.efekos.itemcontent.items.tag;

public class MapTag extends ItemTag{
    private final Integer map;

    public MapTag(ItemTag oldItemTag, Integer map) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.map = map;
    }

    public Integer getMap() {
        return map;
    }
}
