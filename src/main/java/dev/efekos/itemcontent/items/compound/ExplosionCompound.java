package dev.efekos.itemcontent.items.compound;

import java.util.List;

/**
 * Represents an explosion effect of a firework.
 */
public class ExplosionCompound {
    private final boolean Flicker;
    private final boolean Trail;
    private final Integer Type;

    private final List<Integer> Colors;
    private final List<Integer> FadeColors;

    public ExplosionCompound(boolean flicker, boolean trail, Integer type, List<Integer> colors, List<Integer> fadeColors) {
        Flicker = flicker;
        Trail = trail;
        Type = type;
        Colors = colors;
        FadeColors = fadeColors;
    }

    public boolean isFlicker() {
        return Flicker;
    }

    public boolean isTrail() {
        return Trail;
    }

    public Integer getType() {
        return Type;
    }

    public List<Integer> getColors() {
        return Colors;
    }

    public List<Integer> getFadeColors() {
        return FadeColors;
    }
}
