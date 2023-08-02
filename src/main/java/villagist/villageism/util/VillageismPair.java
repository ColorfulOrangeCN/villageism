package villagist.villageism.util;

import java.security.Key;

public class VillageismPair<KeyType> {
    private final KeyType key;
    private final long value;

    public VillageismPair(KeyType key, long value) {
        this.key = key;
        this.value = value;
    }


    public boolean match(KeyType key) {
        return this.key == key;
    }

    public long getValue() {
        return value;
    }

    public boolean isBigger(VillageismPair<KeyType> rhs) {
        return value > rhs.value;
    }


}
