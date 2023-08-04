package villagist.villageism.villager;

import java.util.Objects;
import java.util.UUID;

public class Attitude {
    public UUID uuid;
    public int attitude;

    public Attitude(UUID uuid, int attitude) {
        this.uuid = uuid;
        this.attitude = attitude;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Attitude) obj;
        return Objects.equals(this.uuid, that.uuid) &&
                this.attitude == that.attitude;
    }
}
