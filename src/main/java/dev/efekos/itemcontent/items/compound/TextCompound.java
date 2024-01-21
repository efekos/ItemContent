package dev.efekos.itemcontent.items.compound;

import net.md_5.bungee.api.ChatColor;

import java.util.List;

/**
 * Represents a JSON text you might be familiar from /tellraw command. Used to store custom names and lores.
 */
public class TextCompound {
    private final List<TextCompound> extra;
    private final boolean italic;
    private final boolean bold;
    private final boolean underlined;
    private final boolean strikethrough;
    private final String color;
    private final String text;
    private final boolean obfuscated;

    public TextCompound(boolean italic, boolean bold, boolean underlined, boolean strikethrough, ChatColor color, String text, boolean obfuscated, List<TextCompound> extra) {
        this.italic = italic;
        this.bold = bold;
        this.underlined = underlined;
        this.strikethrough = strikethrough;
        this.color = String.format("#%02x%02x%02x", color.getColor().getRed(), color.getColor().getGreen(), color.getColor().getBlue());
        this.text = text;
        this.obfuscated = obfuscated;
        this.extra = extra;
    }

    public TextCompound(boolean italic, boolean bold, boolean underlined, boolean strikethrough, String color, String text, boolean obfuscated,List<TextCompound> extra) {
        this.italic = italic;
        this.bold = bold;
        this.underlined = underlined;
        this.strikethrough = strikethrough;
        this.color = color;
        this.text = text;
        this.obfuscated = obfuscated;
        this.extra = extra;
    }

    public List<TextCompound> getExtra() {
        return extra;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public boolean isStrikethrough() {
        return strikethrough;
    }

    public String getColor() {
        return color;
    }

    public String getText() {
        return text;
    }

    public boolean isObfuscated() {
        return obfuscated;
    }
}
