package dev.efekos.itemcontent.items.compound;

/**
 * Represents the data inside "Fireworks" tag of a firework.
 */
public class FireworkCompound {
    private final Integer Flight;

    private final ExplosionCompound[] Explosions;

    public FireworkCompound(Integer flight, ExplosionCompound[] explosions) {
        Flight = flight;
        Explosions = explosions;
    }

    public Integer getFlight() {
        return Flight;
    }

    public ExplosionCompound[] getExplosions() {
        return Explosions;
    }
}
