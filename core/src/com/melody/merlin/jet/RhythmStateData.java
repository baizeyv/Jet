package com.melody.merlin.jet;

/**
 * 基本完成
 */
public class RhythmStateData {
    final ArchitectureData architectureData;

    public RhythmStateData(ArchitectureData architectureData) {
        if (architectureData == null)
            throw new IllegalArgumentException("architectureData cannot be null.");
        this.architectureData = architectureData;
    }

    public ArchitectureData getArchitectureData() {
        return architectureData;
    }

    static class Key {
        Rhythm r1, r2;

        @Override
        public int hashCode() {
            return 31 * (31 + r1.hashCode()) + r2.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            Key other = (Key) obj;
            if (r1 == null) {
                if (other.r1 != null)
                    return false;
            } else if (!r1.equals(other.r1)) {
                return false;
            }
            if (r2 == null) {
                if (other.r2 != null)
                    return false;
            } else if (!r2.equals(other.r2)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return r1.name + "->" + r2.name;
        }
    }
}
