package villagist.villageism.villager;

import com.google.gson.Gson;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public interface WithAttitudes {
    String ATTITUDES_KEY = "AttitudesList";
    Attitude[] getAttitudes();
    void setAttitudes(Attitude[] attitudes);
    default void writeToNbt(@NotNull NbtCompound nbt) {
        nbt.putString(ATTITUDES_KEY, new Gson().toJson(getAttitudes()));
    }
    default void loadFromNbt(@NotNull NbtCompound nbt) {
        setAttitudes(new Gson().fromJson(nbt.getString(ATTITUDES_KEY), Attitude[].class));
    }
    default int getAttitudeTo(@NotNull LivingEntity entity) {
        for (Attitude attitude : getAttitudes()) {
            if (attitude.uuid.equals(entity.getUuid())) {
                return attitude.attitude;
            }
        }
        return DefaultsAttitudes.getDefaultAttitude(entity.getType());
    }
}
