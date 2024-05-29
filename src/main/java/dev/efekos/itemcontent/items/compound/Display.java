package dev.efekos.itemcontent.items.compound;

import com.google.gson.Gson;

import java.util.Arrays;

public class Display {
    private final String Name;
    private final String[] Lore;

    public Display(TextCompound[] name, TextCompound[][] lore) {
        this.Name = name != null ? new Gson().toJson(name) : null;
        this.Lore = Arrays.stream(lore).map(textCompounds -> new Gson().toJson(textCompounds)).toArray(String[]::new);
    }

    public Display(TextCompound[] name) {
        this.Name = name != null ? new Gson().toJson(name) : null;
        this.Lore = null;
    }

    public String getName() {
        return Name;
    }

    public String[] getLore() {
        return Lore;
    }
}
