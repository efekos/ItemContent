package dev.efekos.itemcontent.items.compound;

import org.bukkit.enchantments.Enchantment;

/**
 * Represents an enchantment with its string id and level.
 */
public class EnchantmentCompound {
    private final String id;
    private final Integer lvl;

    public EnchantmentCompound(String id, Integer lvl) {
        this.id = id;
        this.lvl = lvl;
    }

    public EnchantmentCompound(Enchantment id, Integer lvl) {
        this.id = id.getKey().getNamespace() + ":" + id.getKey().getKey();
        this.lvl = lvl;
    }

    public String getId() {
        return id;
    }

    public Integer getLvl() {
        return lvl;
    }
}
