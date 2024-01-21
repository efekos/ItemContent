package dev.efekos.itemcontent.items.compound;

/**
 * Represents a pattern inside a banner.
 */
public class BannerPatternCompound {
    private final String Pattern;
    private final Integer Color;

    public BannerPatternCompound(String pattern, Integer color) {
        Pattern = pattern;
        Color = color;
    }

    public String getPattern() {
        return Pattern;
    }

    public Integer getColor() {
        return Color;
    }
}
