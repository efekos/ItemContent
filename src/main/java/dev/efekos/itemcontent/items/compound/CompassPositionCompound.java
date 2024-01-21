package dev.efekos.itemcontent.items.compound;

/**
 * Represents a position inside a lodestone compass.
 */
public class CompassPositionCompound {
        private final Integer X;
        private final Integer Y;
        private final Integer Z;

        public CompassPositionCompound(Integer x, Integer y, Integer z) {
            X = x;
            Y = y;
            Z = z;
        }

        public Integer getX() {
            return X;
        }

        public Integer getY() {
            return Y;
        }

        public Integer getZ() {
            return Z;
        }
}
