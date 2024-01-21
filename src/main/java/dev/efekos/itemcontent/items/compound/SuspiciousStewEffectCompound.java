package dev.efekos.itemcontent.items.compound;

/**
 * Represents an effect of the "Suspicious Stew" item.
 */
public class SuspiciousStewEffectCompound {
    private final Integer effect_id;
    private final Integer effect_duration;

    public SuspiciousStewEffectCompound(Integer effectId, Integer effectDuration) {
        effect_id = effectId;
        effect_duration = effectDuration;
    }

    public Integer getEffectId() {
        return effect_id;
    }

    public Integer getEffectDuration() {
        return effect_duration;
    }
}
