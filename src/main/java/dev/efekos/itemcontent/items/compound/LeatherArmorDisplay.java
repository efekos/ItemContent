package dev.efekos.itemcontent.items.compound;

public class LeatherArmorDisplay extends Display {
    private final Integer color;

    public LeatherArmorDisplay(TextCompound[] name, TextCompound[][] lore, Integer color) {
        super(name, lore);
        this.color = color;
    }

    public Integer getColor() {
        return color;
    }
}
