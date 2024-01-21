package dev.efekos.itemcontent.items.compound;

import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

/**
 * Represents an armor trim inside an armor data.
 */
public class TrimCompound {
    private final String pattern;
    private final String material;

    public TrimCompound(String pattern, String material) {
        this.pattern = pattern;
        this.material = material;
    }

    public TrimCompound(TrimPattern pattern, TrimMaterial material){
        this.pattern = pattern.getKey().getNamespace()+":"+pattern.getKey().getKey();
        this.material = material.getKey().getNamespace()+":"+material.getKey().getKey();
    }

    public String getPattern() {
        return pattern;
    }

    public String getMaterial() {
        return material;
    }
}
