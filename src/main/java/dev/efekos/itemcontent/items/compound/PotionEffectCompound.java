package dev.efekos.itemcontent.items.compound;


import org.bukkit.NamespacedKey;

/**
 * Represents a custom potion effect inside a potion.
 */
public class PotionEffectCompound {
    private final String Id;
    private final Integer Duration;
    private final Integer Amplifier;
    private final boolean ShowParticles;
    private final boolean Ambient;
    private final boolean ShowIcon;

    public PotionEffectCompound(NamespacedKey id, Integer duration, Integer amplifier, boolean showParticles, boolean ambient, boolean showIcon) {
        this.Id = id.getNamespace()+":"+id.getKey();
        this.Duration = duration;
        this.Amplifier = amplifier;
        ShowParticles = showParticles;
        this.Ambient = ambient;
        ShowIcon = showIcon;
    }

    public NamespacedKey getId() {
        return NamespacedKey.fromString(Id);
    }

    public Integer getDuration() {
        return Duration;
    }

    public Integer getAmplifier() {
        return Amplifier;
    }

    public boolean doesShowParticles() {
        return ShowParticles;
    }

    public boolean isAmbient() {
        return Ambient;
    }

    public boolean doesShowIcon() {
        return ShowIcon;
    }
}