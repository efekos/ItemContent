package dev.efekos.itemcontent.items.compound;

/**
 * Represents a banner item.
 */
public class BannerCompound {
    private final Integer Base;
    private final BannerPatternCompound[] Patterns;

    public BannerCompound(Integer base, BannerPatternCompound[] patterns) {
        Base = base;
        Patterns = patterns;
    }

    public Integer getBase() {
        return Base;
    }

    public BannerPatternCompound[] getPatterns() {
        return Patterns;
    }
}
