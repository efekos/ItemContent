package dev.efekos.itemcontent.items.compound;


/**
 * Represents a custom potion effect inside a potion.
 */
public class PotionEffectCompound {
    private final Integer id;
    private final Integer duration;
    private final Integer amplifier;
    private final boolean show_particles;
    private final boolean ambient;
    private final boolean show_icon;

    public PotionEffectCompound(Integer id, Integer duration, Integer amplifier, boolean showParticles, boolean ambient, boolean showIcon) {
        this.id = id;
        this.duration = duration;
        this.amplifier = amplifier;
        show_particles = showParticles;
        this.ambient = ambient;
        show_icon = showIcon;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getAmplifier() {
        return amplifier;
    }

    public boolean doesShowParticles() {
        return show_particles;
    }

    public boolean isAmbient() {
        return ambient;
    }

    public boolean doesShowIcon() {
        return show_icon;
    }
}