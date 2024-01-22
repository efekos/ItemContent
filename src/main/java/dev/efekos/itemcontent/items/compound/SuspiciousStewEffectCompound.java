package dev.efekos.itemcontent.items.compound;

import dev.efekos.itemcontent.items.ItemContent;
import org.bukkit.NamespacedKey;

/**
 * Represents an effect of the "Suspicious Stew" item.
 */
public class SuspiciousStewEffectCompound {
    private final String EffectId;
    private final Integer EffectDuration;

    public SuspiciousStewEffectCompound(NamespacedKey effectId, Integer effectDuration) {
        EffectId = effectId.getNamespace()+":"+effectId.getKey();
        EffectDuration = effectDuration;
    }

    public NamespacedKey getEffectId() {
        return ItemContent.namespacedKeyFromString(EffectId);
    }

    public Integer getEffectDuration() {
        return EffectDuration;
    }
}
